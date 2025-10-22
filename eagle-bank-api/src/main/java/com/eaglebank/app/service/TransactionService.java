package com.eaglebank.app.service;

import com.eaglebank.app.model.BankAccount;
import com.eaglebank.app.model.dto.*;
import com.eaglebank.app.model.Transaction;
import com.eaglebank.app.repository.AccountRepository;
import com.eaglebank.app.repository.TransactionRepository;
import jakarta.transaction.Transactional;
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

    @Autowired
    AccountRepository accountRepository;

    @Transactional
    public TransactionResponse createTransaction(String accountNumber, @Valid CreateTransactionRequest req) {
        BankAccount account = accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        double amount = req.amount();
        if (req.type().equals("withdrawal") && account.getBalance() < amount) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds");
        }
        double newBalance = account.getBalance() + (req.type().equals("deposit") ? amount : -amount);
        account.setBalance(newBalance);
        accountRepository.saveAndFlush(account);

        String id = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
        Transaction t = new Transaction(id, amount, req.currency(), req.type(), req.userId());
        transactionRepository.saveAndFlush(t);

        return new TransactionResponse(t.getId(), t.getAmount(), t.getCurrency(), t.getType(), t.getUserId());
    }

}
