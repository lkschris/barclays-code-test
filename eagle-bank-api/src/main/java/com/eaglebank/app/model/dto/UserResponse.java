package com.eaglebank.app.model.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String address,
        String phoneNumber,
        String email,
        OffsetDateTime createdTimestamp,
        OffsetDateTime updatedTimestamp
) {
}
