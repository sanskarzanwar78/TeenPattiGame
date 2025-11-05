package com.teenpatti.gameengine.service;

import com.teenpatti.common.models.GameRound;
import com.teenpatti.gameengine.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameEngineService {
    
    @Autowired
    private DeckService deckService;
    
    // Store player cards for each round
    private final Map<Long, Map<Long, List<Card>>> roundPlayerCards = new ConcurrentHashMap<>();
    
    public void startNewRound(GameRound round) {
        deckService.shuffle();
        roundPlayerCards.put(round.getId(), new ConcurrentHashMap<>());
    }
    
    public List<Card> dealCardsToPlayer(Long roundId, Long playerId) {
        List<Card> cards = deckService.drawCards(3);
        roundPlayerCards.get(roundId).put(playerId, cards);
        return cards;
    }
    
    public List<Card> getPlayerCards(Long roundId, Long playerId) {
        return roundPlayerCards.get(roundId).get(playerId);
    }
    
    public void endRound(Long roundId) {
        roundPlayerCards.remove(roundId);
    }
    
    // Add game logic methods for comparing hands, calculating winners, etc.
}