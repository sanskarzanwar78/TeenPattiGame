package com.teenpatti.realtime.controller;

import com.teenpatti.common.models.Card;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GameWebSocketController {
    
    @MessageMapping("/game/{roomId}/join")
    @SendTo("/topic/game/{roomId}/players")
    public String handleJoin(String username) {
        return username + " joined the game";
    }
    
    @MessageMapping("/game/{roomId}/bet")
    @SendTo("/topic/game/{roomId}/bets")
    public String handleBet(String betInfo) {
        return betInfo;
    }
    
    @MessageMapping("/game/{roomId}/action")
    @SendTo("/topic/game/{roomId}/actions")
    public String handleAction(String action) {
        return action;
    }
    
    @MessageMapping("/game/{roomId}/cards")
    @SendTo("/topic/game/{roomId}/cards")
    public List<Card> handleCards(List<Card> cards) {
        return cards;
    }
}