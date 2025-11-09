package com.teenpatti.transaction.repository;

import com.teenpatti.common.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserId(Long userId);
    List<Transaction> findByGameRoundId(Long gameRoundId);
    List<Transaction> findByUserIdAndType(Long userId, String type);
}