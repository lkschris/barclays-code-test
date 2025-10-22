package com.eaglebank.app.model;

import java.time.OffsetDateTime;
import java.util.UUID;

public class User {
    private final UUID id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;


    public User(UUID id, String name, String address, String phoneNumber, String email, OffsetDateTime createdTimestamp, OffsetDateTime updatedTimestamp) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}