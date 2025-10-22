package com.eaglebank.app.model.dto;

import java.util.List;

public record ListTransactionsResponse(List<TransactionResponse> transactions) {
}
