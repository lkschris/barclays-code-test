package com.eaglebank.app.controller;

import com.eaglebank.app.model.dto.BankAccountResponse;
import com.eaglebank.app.model.dto.CreateBankAccountRequest;
import com.eaglebank.app.model.dto.ListBankAccountsResponse;
import com.eaglebank.app.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<BankAccountResponse> createAccount(@Valid @RequestBody CreateBankAccountRequest req) {
        BankAccountResponse resp = accountService.createAccount(req);
        return ResponseEntity.status(201).body(resp);
    }

    @GetMapping
    public ResponseEntity<ListBankAccountsResponse> listAccounts() {
        return ResponseEntity.ok(accountService.listAccounts());
    }
}
