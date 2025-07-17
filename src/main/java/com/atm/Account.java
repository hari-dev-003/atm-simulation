package com.atm;

public class Account {
    private String accountNumber;
    private String username;
    private String passwordHash;
    private double balance;

    public Account(String accountNumber, String username, String passwordHash, double balance) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.passwordHash = passwordHash;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) return false;
        this.balance -= amount;
        return true;
    }
}
