package com.example.procon34_CYBER_WARS_backend.repository;

import org.springframework.stereotype.Repository;

import com.example.procon34_CYBER_WARS_backend.dto.rooms.CreateRoomRequest;
import com.example.procon34_CYBER_WARS_backend.mapper.RoomsMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RoomsRepository {

    private final RoomsMapper roomsMapper;

    // ルーム作成
    public void createRoom(final CreateRoomRequest createRoomRequest) {
        roomsMapper.createRoom(createRoomRequest);
    }

    // ルーム割り当て
    public void allocateRoom(final CreateRoomRequest createRoomRequest) {
        roomsMapper.allocateRoom(createRoomRequest);
    }

}
