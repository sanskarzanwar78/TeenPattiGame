package com.teenpatti.transaction.controller;

import com.teenpatti.common.models.Transaction;
import com.teenpatti.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/bet")
    public ResponseEntity<Transaction> placeBet(@RequestBody Transaction bet) {
        // bet.type = "BET" or "DEBIT"
        Transaction tx = transactionService.createTransaction(bet);
        return ResponseEntity.ok(tx);
    }

    @PostMapping("/win")
    public ResponseEntity<Transaction> addWinnings(@RequestBody Transaction win) {
        // win.type = "WIN" or "CREDIT"
        Transaction tx = transactionService.createTransaction(win);
        return ResponseEntity.ok(tx);
    }

    @GetMapping("/balance/{userId}")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable Long userId) {
        BigDecimal balance = transactionService.calculateUserBalance(userId);
        return ResponseEntity.ok(balance);
    }

    @GetMapping("/transactions/{userId}")
    public ResponseEntity<List<Transaction>> getUserTransactions(@PathVariable Long userId) {
        List<Transaction> txs = transactionService.getUserTransactions(userId);
        return ResponseEntity.ok(txs);
    }
}
