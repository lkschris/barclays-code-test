package com.eaglebank.app.model;

import java.time.OffsetDateTime;

public class Transaction {
    private final String id;
    private final double amount;
    private final String currency;
    private final String type;
    private final String userId;

    public Transaction(String id, double amount, String currency, String type, String userId) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.type = type;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getType() {
        return type;
    }

    public String getUserId() {
        return userId;
    }

}
