package io.sruby.github.test.unit.account;

import javax.security.auth.login.AccountLockedException;
import java.security.InvalidParameterException;

/**
 * @description: account
 * @author: sruby
 * @create: 2020-12-22 17:24
 */
public class Account {
    private boolean locked;
    private int balance = 0;
    /**
     * 记录详细开支
     */
    private Transactions transactions;

    /**
     * deposit
     * @param amount
     * @throws AccountLockedException
     */
    public void deposit(int amount) throws AccountLockedException, InvalidAmountException {
        if (locked){
            throw new AccountLockedException("account locked!");
        }
        if (amount < 0){
            throw new InvalidAmountException("balance less 0!");
        }
        balance += amount;
        transactions.add(this,TransactionType.DEBIT,amount);
    }

    /**
     * withdraw
     * @param amount
     */
    public void withdraw(int amount) throws AccountLockedException, InvalidAmountException {
        if (locked){
            throw new AccountLockedException("account locked!");
        }
        if (amount < 0){
            throw new InvalidAmountException("balance less 0!");
        }
        if (amount > balance){
            throw new InvalidParameterException("amount more balance");
        }
        balance -= amount;
        transactions.add(this,TransactionType.CREDIT,amount);
    }

    public void lock(){
        this.locked = true;
    }

    public void unLock(){
        this.locked = false;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Transactions getTransactions() {
        return transactions;
    }

    public void setTransactions(Transactions transactions) {
        this.transactions = transactions;
    }


}
