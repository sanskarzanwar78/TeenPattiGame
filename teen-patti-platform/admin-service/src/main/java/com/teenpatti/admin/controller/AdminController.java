package com.teenpatti.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    
    private final RestTemplate restTemplate;
    
    public AdminController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    @GetMapping("/users/stats")
    public ResponseEntity<?> getUserStats() {
        // Get user statistics from user-service
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/games/stats")
    public ResponseEntity<?> getGameStats() {
        // Get game statistics from game-room-service
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/transactions/stats")
    public ResponseEntity<?> getTransactionStats() {
        // Get transaction statistics from transaction-service
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/rooms/{roomId}/close")
    public ResponseEntity<?> closeGameRoom(@PathVariable Long roomId) {
        // Close a game room through game-room-service
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/users/{userId}/block")
    public ResponseEntity<?> blockUser(@PathVariable Long userId) {
        // Block a user through user-service
        return ResponseEntity.ok().build();
    }
}