package com.teenpatti.gameengine.controller;

import com.teenpatti.common.models.GameRound;
import com.teenpatti.gameengine.model.Card;
import com.teenpatti.gameengine.service.GameEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game")
public class GameEngineController {
    private final GameEngineService gameEngineService;

    @Autowired
    public GameEngineController(GameEngineService gameEngineService) {
        this.gameEngineService = gameEngineService;
    }

    @PostMapping("/round/start")
    public ResponseEntity<String> startRound(@RequestBody GameRound round) {
        gameEngineService.startNewRound(round);
        return ResponseEntity.ok("Round started");
    }

    @PostMapping("/round/{roundId}/deal/{playerId}")
    public ResponseEntity<List<Card>> dealCards(@PathVariable Long roundId, @PathVariable Long playerId) {
        List<Card> cards = gameEngineService.dealCardsToPlayer(roundId, playerId);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/round/{roundId}/player/{playerId}/cards")
    public ResponseEntity<List<Card>> getPlayerCards(@PathVariable Long roundId, @PathVariable Long playerId) {
        List<Card> cards = gameEngineService.getPlayerCards(roundId, playerId);
        return ResponseEntity.ok(cards);
    }

    @PostMapping("/round/{roundId}/end")
    public ResponseEntity<String> endRound(@PathVariable Long roundId) {
        gameEngineService.endRound(roundId);
        return ResponseEntity.ok("Round ended");
    }

    @GetMapping("/round/{roundId}/winner")
    public ResponseEntity<Long> getWinner(@PathVariable Long roundId) {
        Long winner = gameEngineService.determineWinner(roundId);
        if (winner == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(winner);
    }
}
