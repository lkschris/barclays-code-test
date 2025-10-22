package com.eaglebank.app.controller;

import com.eaglebank.app.model.dto.CreateBankAccountRequest;
import com.eaglebank.app.model.dto.CreateTransactionRequest;
import com.eaglebank.app.model.dto.ListTransactionsResponse;
import com.eaglebank.app.model.dto.TransactionResponse;
import com.eaglebank.app.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/accounts/{accountNumber}/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public TransactionResponse createTransactions(@PathVariable String accountNumber, @Valid @RequestBody CreateTransactionRequest req) {
        return transactionService.createTransaction(accountNumber, req);
    }
}
