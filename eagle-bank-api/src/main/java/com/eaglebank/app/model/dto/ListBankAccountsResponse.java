package com.eaglebank.app.model.dto;

import java.util.List;

public record ListBankAccountsResponse(List<BankAccountResponse> accounts) {
}
