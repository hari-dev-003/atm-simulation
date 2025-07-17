package com.atm;

public class App {
    public static void main(String[] args) {
        AuthService authService = new AuthService();
        TransactionService transactionService = new TransactionService();
        ATM atm = new ATM(authService, transactionService);
        atm.start();
    }
}
