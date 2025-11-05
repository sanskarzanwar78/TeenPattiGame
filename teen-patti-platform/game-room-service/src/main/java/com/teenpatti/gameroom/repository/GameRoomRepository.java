package com.teenpatti.gameroom.repository;

import com.teenpatti.common.models.GameRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRoomRepository extends JpaRepository<GameRoom, Long> {
    List<GameRoom> findByStatus(String status);
    List<GameRoom> findByCurrentPlayersLessThanAndStatus(int maxPlayers, String status);
}