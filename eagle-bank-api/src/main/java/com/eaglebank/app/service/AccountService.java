package com.eaglebank.app.service;

import com.eaglebank.app.model.BankAccount;
import com.eaglebank.app.model.dto.BankAccountResponse;
import com.eaglebank.app.model.dto.CreateBankAccountRequest;
import com.eaglebank.app.model.dto.ListBankAccountsResponse;
import com.eaglebank.app.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public BankAccountResponse createAccount(CreateBankAccountRequest req) {
        String accountNumber = "01" + String.format("%06d", (int) (Math.random() * 1_000_000));
        String sortCode = "10-10-10";
        OffsetDateTime now = OffsetDateTime.now();
        BankAccount acc = new BankAccount(
                accountNumber, req.name(), req.accountType(), 0.0, "GBP");
        accountRepository.saveAndFlush(acc);
        return new BankAccountResponse(accountNumber, req.name(), req.accountType(), 0.0, "GBP");
    }

    public ListBankAccountsResponse listAccounts() {
        List<BankAccountResponse> resp = accountRepository.findALlById().stream()
                .map(a -> new BankAccountResponse(a.getAccountNumber(), a.getName(), a.getAccountType(), a.getBalance(), a.getCurrency()))
                .collect(Collectors.toList());
        return new ListBankAccountsResponse(resp);
    }
}
