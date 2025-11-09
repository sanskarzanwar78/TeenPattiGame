package com.teenpatti.gameroom.controller;

import com.teenpatti.common.models.GameRoom;
import com.teenpatti.gameroom.service.GameRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class GameRoomController {
    
    @Autowired
    private GameRoomService gameRoomService;
    
    @PostMapping
    public ResponseEntity<GameRoom> createRoom(@RequestBody GameRoom room) {
        return ResponseEntity.ok(gameRoomService.createRoom(room));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<GameRoom> getRoom(@PathVariable String id) {
        return ResponseEntity.ok(gameRoomService.getRoom(id));
    }
    
    @GetMapping
    public ResponseEntity<List<GameRoom>> getAvailableRooms() {
        return ResponseEntity.ok(gameRoomService.getAvailableRooms());
    }
    
    @PostMapping("/{id}/join")
    public ResponseEntity<GameRoom> joinRoom(@PathVariable String id) {
        return ResponseEntity.ok(gameRoomService.joinRoom(id));
    }
    
    @PostMapping("/{id}/leave")
    public ResponseEntity<GameRoom> leaveRoom(@PathVariable String id) {
        return ResponseEntity.ok(gameRoomService.leaveRoom(id));
    }
    
    @PostMapping("/{id}/close")
    public ResponseEntity<Void> closeRoom(@PathVariable String id) {
        gameRoomService.closeRoom(id);
        return ResponseEntity.ok().build();
    }
}