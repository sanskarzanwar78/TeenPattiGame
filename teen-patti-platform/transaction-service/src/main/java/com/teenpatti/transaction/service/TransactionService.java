package com.teenpatti.transaction.service;

import com.teenpatti.transaction.model.Transaction;
import com.teenpatti.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {
    
    @Autowired
    private TransactionRepository transactionRepository;
    
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
    
    public List<Transaction> getUserTransactions(Long userId) {
        return transactionRepository.findByUserId(userId);
    }
    
    public List<Transaction> getGameRoundTransactions(Long gameRoundId) {
        return transactionRepository.findByGameRoundId(gameRoundId);
    }
    
    public BigDecimal calculateUserBalance(Long userId) {
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        return transactions.stream()
            .map(t -> "CREDIT".equals(t.getType()) ? t.getAmount() : t.getAmount().negate())
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}