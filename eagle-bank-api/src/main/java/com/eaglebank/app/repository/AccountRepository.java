package com.eaglebank.app.repository;

import com.eaglebank.app.model.BankAccount;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<BankAccount, UUID> {
    boolean existsByUserId(UUID userId);
}
