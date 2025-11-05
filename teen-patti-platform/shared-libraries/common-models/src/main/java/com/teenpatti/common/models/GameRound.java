package com.teenpatti.common.models;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "game_rounds")
public class GameRound {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "room_id")
    private Long roomId;
    
    private String status;
    
    @Column(name = "pot_amount")
    private BigDecimal potAmount;
    
    @Column(name = "winner_id")
    private Long winnerId;
    
    private LocalDateTime startTime;
    
    private LocalDateTime endTime;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        startTime = LocalDateTime.now();
        potAmount = BigDecimal.ZERO;
    }
}