package com.example.procon34_CYBER_WARS_backend.utility.rooms;

import org.springframework.stereotype.Component;

import com.example.procon34_CYBER_WARS_backend.repository.AllocationsRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoomIdFetcher {

    private final AllocationsRepository allocationsRepository;

    // ルームID取得
    public int fetchRoomId(final int userId) {
        return allocationsRepository.fetchRoomId(userId);
    }

}
