package com.teenpatti.leaderboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LeaderboardService {
    
    private static final String WEEKLY_LEADERBOARD = "weekly_leaderboard";
    private static final String MONTHLY_LEADERBOARD = "monthly_leaderboard";
    private static final String ALL_TIME_LEADERBOARD = "all_time_leaderboard";
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    public void updateScore(String userId, double score) {
        redisTemplate.opsForZSet().add(WEEKLY_LEADERBOARD, userId, score);
        redisTemplate.opsForZSet().add(MONTHLY_LEADERBOARD, userId, score);
        redisTemplate.opsForZSet().add(ALL_TIME_LEADERBOARD, userId, score);
    }
    
    public Set<String> getWeeklyTopPlayers(int count) {
        return redisTemplate.opsForZSet().reverseRange(WEEKLY_LEADERBOARD, 0, count - 1);
    }
    
    public Set<String> getMonthlyTopPlayers(int count) {
        return redisTemplate.opsForZSet().reverseRange(MONTHLY_LEADERBOARD, 0, count - 1);
    }
    
    public Set<String> getAllTimeTopPlayers(int count) {
        return redisTemplate.opsForZSet().reverseRange(ALL_TIME_LEADERBOARD, 0, count - 1);
    }
    
    public Double getUserRank(String userId, String leaderboardType) {
        String key = switch (leaderboardType) {
            case "weekly" -> WEEKLY_LEADERBOARD;
            case "monthly" -> MONTHLY_LEADERBOARD;
            default -> ALL_TIME_LEADERBOARD;
        };
        return redisTemplate.opsForZSet().score(key, userId);
    }
}