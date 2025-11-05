package com.teenpatti.gameroom.service;

import com.teenpatti.common.models.GameRoom;
import com.teenpatti.gameroom.repository.GameRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameRoomService {
    
    @Autowired
    private GameRoomRepository gameRoomRepository;
    
    public GameRoom createRoom(GameRoom room) {
        room.setStatus("WAITING");
        return gameRoomRepository.save(room);
    }
    
    public GameRoom getRoom(Long id) {
        return gameRoomRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Room not found"));
    }
    
    public List<GameRoom> getAvailableRooms() {
        return gameRoomRepository.findByCurrentPlayersLessThanAndStatus(
            Integer.MAX_VALUE, "WAITING");
    }
    
    public GameRoom joinRoom(Long roomId) {
        GameRoom room = getRoom(roomId);
        if (room.getCurrentPlayers() >= room.getMaxPlayers()) {
            throw new RuntimeException("Room is full");
        }
        room.setCurrentPlayers(room.getCurrentPlayers() + 1);
        return gameRoomRepository.save(room);
    }
    
    public GameRoom leaveRoom(Long roomId) {
        GameRoom room = getRoom(roomId);
        if (room.getCurrentPlayers() > 0) {
            room.setCurrentPlayers(room.getCurrentPlayers() - 1);
        }
        return gameRoomRepository.save(room);
    }
    
    public void closeRoom(Long roomId) {
        GameRoom room = getRoom(roomId);
        room.setStatus("CLOSED");
        gameRoomRepository.save(room);
    }
}