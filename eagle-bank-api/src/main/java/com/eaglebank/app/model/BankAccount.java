package com.eaglebank.app.model;

import java.time.OffsetDateTime;

public class BankAccount {

    private final String accountNumber;
    private String name;
    private String accountType;
    private double balance;
    private final String currency;

    public BankAccount(String accountNumber, String name, String accountType, double balance, String currency, OffsetDateTime createdTimestamp, OffsetDateTime updatedTimestamp) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.accountType = accountType;
        this.balance = balance;
        this.currency = currency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

}
