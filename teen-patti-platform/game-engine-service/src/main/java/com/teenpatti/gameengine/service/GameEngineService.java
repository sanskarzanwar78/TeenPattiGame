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
    
    // Compare two hands and return -1 if hand1 wins, 1 if hand2 wins, 0 if tie
    public int compareHands(List<Card> hand1, List<Card> hand2) {
        // Simplified: sort by rank, compare highest card
        hand1.sort((a, b) -> a.getRank().ordinal() - b.getRank().ordinal());
        hand2.sort((a, b) -> a.getRank().ordinal() - b.getRank().ordinal());
        for (int i = 2; i >= 0; i--) {
            int cmp = hand1.get(i).getRank().ordinal() - hand2.get(i).getRank().ordinal();
            if (cmp != 0) return cmp < 0 ? 1 : -1;
        }
        return 0;
    }

    // Determine winner among players in a round
    public Long determineWinner(Long roundId) {
        Map<Long, List<Card>> playerHands = roundPlayerCards.get(roundId);
        if (playerHands == null || playerHands.size() < 2) return null;
        Long winner = null;
        List<Card> bestHand = null;
        for (Map.Entry<Long, List<Card>> entry : playerHands.entrySet()) {
            if (winner == null) {
                winner = entry.getKey();
                bestHand = entry.getValue();
            } else {
                int cmp = compareHands(bestHand, entry.getValue());
                if (cmp > 0) {
                    winner = entry.getKey();
                    bestHand = entry.getValue();
                }
            }
        }
        return winner;
    }
}