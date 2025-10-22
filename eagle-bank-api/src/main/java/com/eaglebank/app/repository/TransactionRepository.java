package com.eaglebank.app.repository;

import com.eaglebank.app.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
