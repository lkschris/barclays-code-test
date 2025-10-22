package com.eaglebank.app.model.dto;

import java.time.OffsetDateTime;

public record BankAccountResponse(
        String accountNumber,
        String name,
        String accountType,
        double balance,
        String currency
) {
}
