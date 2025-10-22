package com.eaglebank.app.model.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record CreateTransactionRequest(
        @NotNull @DecimalMin("0.00") @DecimalMax("10000.00") Double amount,
        @NotNull String currency,
        @NotNull String type,
        String userId
) {
}
