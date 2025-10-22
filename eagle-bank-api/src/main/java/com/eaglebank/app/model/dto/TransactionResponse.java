package com.eaglebank.app.model.dto;

import java.time.OffsetDateTime;

public record TransactionResponse(
        String id,
        double amount,
        String currency,
        String type,
        String userId
) {
}
