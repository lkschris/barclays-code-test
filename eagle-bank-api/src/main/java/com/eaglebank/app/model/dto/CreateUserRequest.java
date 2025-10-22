package com.eaglebank.app.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateUserRequest(
        @NotBlank String name,
        @NotBlank String address,
        @NotBlank @Pattern(regexp = "^\\+[1-9]\\\\d{1,14}$") String phoneNumber,
        @NotBlank String email
) {
    public static record Address(
            @NotBlank String line1,
            String line2,
            String line3,
            @NotBlank String town,
            @NotBlank String county,
            @NotBlank String postcode
    ) {
    }
}
