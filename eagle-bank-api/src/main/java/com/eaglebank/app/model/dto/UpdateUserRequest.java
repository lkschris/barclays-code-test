package com.eaglebank.app.model.dto;

public record UpdateUserRequest(String name, String address, String phoneNumber, String email) {
}
