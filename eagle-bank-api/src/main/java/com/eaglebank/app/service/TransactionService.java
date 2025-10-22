package com.eaglebank.app.service;

import com.eaglebank.app.model.dto.*;
import com.eaglebank.app.model.Transaction;
import com.eaglebank.app.repository.TransactionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public TransactionResponse createTransaction(String accountNumber, @Valid CreateTransactionRequest req) {
        var account = transactionRepository.getAccount(accountNumber).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        double amount = req.amount();
        if (req.type().equals("withdrawal") && account.getBalance() < amount) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds");
        }
        double newBalance = account.getBalance() + (req.type().equals("deposit") ? amount : -amount);
        account.setBalance(newBalance);
        account.setUpdatedTimestamp(OffsetDateTime.now());

        String id = "tan-" + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        Transaction t = new Transaction(id, amount, req.currency(), req.type(), req.reference(), null);
        storage.saveTransaction(accountNumber, t);

        return new TransactionResponse(t.getId(), t.getAmount(), t.getCurrency(), t.getType(), t.getReference(), t.getUserId());
    }

    public ListTransactionsResponse listTransactions(String accountNumber) {
        var transactions = storage.getTransactions(accountNumber).stream()
                .map(t -> new TransactionResponse(t.getId(), t.getAmount(), t.getCurrency(), t.getType(), t.getReference(), t.getUserId()))
                .collect(Collectors.toList());
        return new ListTransactionsResponse(transactions);
    }
}
