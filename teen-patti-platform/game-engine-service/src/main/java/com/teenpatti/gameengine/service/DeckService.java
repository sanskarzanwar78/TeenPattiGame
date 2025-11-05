package com.teenpatti.gameengine.service;

import com.teenpatti.gameengine.model.Card;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DeckService {
    private List<Card> deck;
    
    public DeckService() {
        initializeDeck();
    }
    
    private void initializeDeck() {
        deck = new ArrayList<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
    }
    
    public void shuffle() {
        Collections.shuffle(deck);
    }
    
    public Card drawCard() {
        if (deck.isEmpty()) {
            initializeDeck();
            shuffle();
        }
        return deck.remove(0);
    }
    
    public List<Card> drawCards(int count) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            cards.add(drawCard());
        }
        return cards;
    }
}