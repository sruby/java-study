package io.sruby.github.test.unit.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.security.auth.login.AccountLockedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

class AccountTest {
    private static final int ORIGINAL_BALANCE = 1000;
    @Mock
    Transactions transactions;
    @InjectMocks
    Account account;

    @BeforeEach
    void setUp() throws InvalidAccountException, AccountLockedException {
        MockitoAnnotations.initMocks(this);
        account.deposit(ORIGINAL_BALANCE);
    }

    @Test
    void testDeposit_ShouldSuccess() throws InvalidAccountException, AccountLockedException {
        int amount = 10;
        account.deposit(amount);
        assertThat(account.getBalance()).isEqualTo(ORIGINAL_BALANCE + amount);
        verify(transactions).add(account,TransactionType.DEBIT, amount);
    }

    @Test
    void testWithdraw() throws InvalidAccountException, AccountLockedException {
        account.withdraw(0);
    }

    @Test
    void testLock() {
        account.lock();
    }

    @Test
    void testUnLock() {
        account.unLock();
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme