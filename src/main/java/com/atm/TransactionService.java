package com.atm;

public class TransactionService {
    public boolean withdrawFunds(Account account, double amount) {
        if (amount <= 0) return false;
        return account.withdraw(amount);
    }

    public boolean transferFunds(Account from, Account to, double amount) {
        if (from == null || to == null) return false;
        if (amount <= 0 || amount > from.getBalance()) return false;
        from.withdraw(amount);
        to.deposit(amount);
        return true;
    }
}
