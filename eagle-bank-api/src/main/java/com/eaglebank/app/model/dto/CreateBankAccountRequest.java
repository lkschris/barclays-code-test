package com.eaglebank.app.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateBankAccountRequest(
        @NotBlank String name,
        @NotNull String accountType
) {
}
