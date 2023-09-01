package com.example.procon34_CYBER_WARS_backend.utility;

import org.springframework.stereotype.Component;

import com.example.procon34_CYBER_WARS_backend.repository.GameRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GameManager {

    private final GameRepository gameRepository;

    // 相手ユーザー名取得
    public String getOpponentName(final int userId, final int roomId) {
        return gameRepository.getOpponentName(userId, roomId);
    }

}
