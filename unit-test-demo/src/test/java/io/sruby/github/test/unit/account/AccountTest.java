package io.sruby.github.test.unit.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.security.auth.login.AccountLockedException;

import java.security.InvalidParameterException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class AccountTest {
    private static final int ORIGINAL_BALANCE = 1000;
    @Mock
    Transactions transactions;
    @InjectMocks
    Account account;

    @BeforeEach
    void setUp() throws InvalidAmountException, AccountLockedException {
        MockitoAnnotations.initMocks(this);
        account.deposit(ORIGINAL_BALANCE);
    }

    @Test
    void testDeposit_ShouldSuccess() throws InvalidAmountException, AccountLockedException {
        int amount = 10;
        account.deposit(amount);
        assertThat(account.getBalance()).isEqualTo(ORIGINAL_BALANCE + amount);
        verify(transactions).add(account,TransactionType.DEBIT, amount);
    }

    @Test
    public void testDeposit_ShouldThrowAccountLockedException_WhenAccountLocked() throws InvalidAmountException, AccountLockedException {
        account.lock();
        assertThrows(AccountLockedException.class,()->account.deposit(10));
        assertThat(account.getBalance()).isEqualTo(ORIGINAL_BALANCE);
        verify(transactions,never()).add(account,TransactionType.DEBIT,10);
    }

    @Test
    public void testDeposit_ShouldThrowInvalidAmountException_WhenAmountLessZero() {
        int amount = -1;
        assertThrows(InvalidAmountException.class,()->account.deposit(amount));
        assertThat(account.getBalance()).isEqualTo(ORIGINAL_BALANCE);
        verify(transactions,never()).add(account,TransactionType.DEBIT, amount);
    }



    @Test
    void testWithdraw_ShouldSuccess() throws AccountLockedException, InvalidAmountException {
        int amount = 10;
        account.withdraw(amount);
        assertThat(account.getBalance()).isEqualTo(ORIGINAL_BALANCE- amount);
        verify(transactions).add(account,TransactionType.CREDIT,amount);
    }
    @Test
    void testWithdraw_ShouldSuccess_WithDrawAll() throws AccountLockedException, InvalidAmountException {
        int amount = ORIGINAL_BALANCE;
        account.withdraw(amount);
        assertThat(account.getBalance()).isEqualTo(ORIGINAL_BALANCE- amount);
        verify(transactions).add(account,TransactionType.CREDIT,amount);
    }
    @Test
    void testWithdraw_ShouldThrowAccountLockedException_WhenAccountLocked() {
        int amount = 10;
        account.lock();
        assertThrows(AccountLockedException.class,()->account.withdraw(amount));
        verify(transactions,never()).add(account,TransactionType.CREDIT,amount);
    }
    @Test
    void testWithdraw_ShouldThrowInvalidAmountException_WhenAccountBalanceLessZero() {
        int amount = -1;
        assertThrows(InvalidAmountException.class,()->account.withdraw(amount));
        verify(transactions,never()).add(account,TransactionType.CREDIT,amount);
    }
    @Test
    void testWithdraw_ShouldThrowInvalidAmountException_WhenAccountBalanceNotEnough() {
        int amount = 1001;
        assertThrows(InvalidParameterException.class,()->account.withdraw(amount));
        verify(transactions,never()).add(account,TransactionType.CREDIT,amount);
    }

    @Test
    void testLock() {
        account.lock();
        assertThat(account.isLocked());
    }

    @Test
    void testUnLock() {
        account.unLock();
        assertThat(!account.isLocked());
    }


}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme