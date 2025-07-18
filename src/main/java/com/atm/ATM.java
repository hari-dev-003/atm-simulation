package com.atm;

import java.util.Scanner;

public class ATM {
    private AuthService authService;
    private TransactionService transactionService;
    private Account currentAccount;
    private Scanner scanner;

    public ATM(AuthService authService, TransactionService transactionService) {
        this.authService = authService;
        this.transactionService = transactionService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("Welcome to ATM Simulator");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose option: ");
            String input = scanner.nextLine();
            if ("1".equals(input)) {
                loginProcess();
            } else if ("2".equals(input)) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Try again\n");
            }
        }
        scanner.close();
    }

    private void loginProcess() {
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();
        currentAccount = authService.login(username, password);
        if (currentAccount == null) {
            System.out.println("Invalid credentials.\n");
            return;
        }
        System.out.println("Login successful!\n");
        accountMenu();
    }

    private void accountMenu() {
        while (true) {
            System.out.println("\n1. View Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Logout");
            System.out.print("Choose option: ");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    System.out.println("Current Balance: ₹" + currentAccount.getBalance());
                    break;
                case "2":
                    withdraw();
                    break;
                case "3":
                    transfer();
                    break;
                case "4":
                    System.out.println("Logged out.\n");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void withdraw() {
        try {
            System.out.print("Enter amount to withdraw: ");
            double amount = Double.parseDouble(scanner.nextLine());
            if (transactionService.withdrawFunds(currentAccount, amount)) {
                System.out.println("Withdrawal successful. New Balance: ₹" + currentAccount.getBalance());
            } else {
                System.out.println("Insufficient funds or invalid amount.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Enter numeric value.");
        }
    }

    private void transfer() {
        try {
            System.out.print("Enter recipient account number: ");
            String toAccNum = scanner.nextLine();
            Account toAccount = authService.getAccountByNumber(toAccNum);

            if (toAccount == null || toAccount == currentAccount) {
                System.out.println("Invalid recipient account number.");
                return;
            }

            System.out.print("Enter amount to transfer: ");
            double amount = Double.parseDouble(scanner.nextLine());

            if (transactionService.transferFunds(currentAccount, toAccount, amount)) {
                System.out.println("Transfer successful. New Balance: ₹" + currentAccount.getBalance());
            } else {
                System.out.println("Transfer failed (check balance/amount).");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Enter numeric value.");
        }
    }
}
