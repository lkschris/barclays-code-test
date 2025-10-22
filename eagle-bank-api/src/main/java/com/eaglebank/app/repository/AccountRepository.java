package com.eaglebank.app.repository;

import com.eaglebank.app.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<BankAccount, UUID> {
    boolean existsByUserId(UUID userId);

    Optional<BankAccount> findByAccountNumber(String accountNumber);
}
