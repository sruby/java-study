package io.sruby.github.test.unit.account;


public interface Transactions {
    void add(Account account, TransactionType debit, int amount);
}
