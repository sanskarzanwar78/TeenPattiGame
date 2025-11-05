package com.teenpatti.gameengine.model;

import lombok.Data;

@Data
public class Card {
    private final Suit suit;
    private final Rank rank;
    
    public enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }
    
    public enum Rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN,
        JACK, QUEEN, KING, ACE
    }
}