package com.teenpatti.transaction.service;

import com.teenpatti.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserId(Long userId);
    List<Transaction> findByGameRoundId(Long gameRoundId);
    List<Transaction> findByUserIdAndType(Long userId, String type);
}