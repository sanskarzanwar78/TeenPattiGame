package com.teenpatti.gameroom.repository;

import com.teenpatti.common.models.GameRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface GameRoomRepository extends MongoRepository<GameRoom, String> {
    List<GameRoom> findByStatus(String status);
    List<GameRoom> findByCurrentPlayersLessThanAndStatus(int maxPlayers, String status);
}