package com.example.procon34_CYBER_WARS_backend.repository;

import org.springframework.stereotype.Repository;

import com.example.procon34_CYBER_WARS_backend.entity.Allocations;
import com.example.procon34_CYBER_WARS_backend.entity.Rooms;
import com.example.procon34_CYBER_WARS_backend.entity.Vulnerabilities;
import com.example.procon34_CYBER_WARS_backend.mapper.RoomsMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RoomsRepository {

    private final RoomsMapper roomsMapper;

    // ルーム作成
    public void createRoom(final Rooms room, final Vulnerabilities vulnerability) {
        roomsMapper.createRoom(room, vulnerability);
    }

    // ルーム割り当て
    public void allocateRoom(final Allocations allocation) {
        roomsMapper.allocateRoom(allocation);
    }

}
