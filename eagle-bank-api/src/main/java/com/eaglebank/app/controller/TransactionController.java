package com.eaglebank.app.controller;

import com.eaglebank.app.model.dto.CreateTransactionRequest;
import com.eaglebank.app.model.dto.ListTransactionsResponse;
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

    @GetMapping
    public ResponseEntity<ListTransactionsResponse> listTransactions(@PathVariable String accountNumber) {
        return ResponseEntity.ok(transactionService.listTransactions(accountNumber));
    }
}
