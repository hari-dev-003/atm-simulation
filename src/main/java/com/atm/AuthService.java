package com.atm;

import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private Map<String, Account> userMap = new HashMap<>();

    // Populate with demo accounts; in real apps, use a db or file
    public AuthService() {
        // Using the actual username as the key for the map makes the login more intuitive.
        userMap.put("Hari", new Account("101", "Hari", "root", 5000));
        userMap.put("Dev", new Account("102", "Dev", "root", 3000));
    }

    public Account login(String username, String password) {
        if (!userMap.containsKey(username))
            return null;
        Account account = userMap.get(username);
       
        if (account.getPasswordHash().equals(password)) {
            return account;
        }
        return null;
    }

    public Account getAccountByNumber(String accNum) {
        for (Account acc : userMap.values()) {
            if (acc.getAccountNumber().equals(accNum)) return acc;
        }
        return null;
    }
}
