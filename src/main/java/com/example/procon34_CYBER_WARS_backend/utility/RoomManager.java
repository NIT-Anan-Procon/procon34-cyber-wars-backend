package com.example.procon34_CYBER_WARS_backend.utility;

import org.springframework.stereotype.Component;

import com.example.procon34_CYBER_WARS_backend.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoomManager {

    private final RoomRepository roomRepository;

    // ルームID取得
    public int getRoomId(final int userId) {
        return roomRepository.getRoomId(userId);
    }

    // ルーム閉鎖
    public void close(final int roomId) {
        roomRepository.close(roomId);
    }

}
