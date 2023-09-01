package com.example.procon34_CYBER_WARS_backend.repository;

import java.sql.Timestamp;

import org.springframework.stereotype.Repository;

import com.example.procon34_CYBER_WARS_backend.mapper.GameMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class GameRepository {

    private final GameMapper gameMapper;

    // ゲーム開始時刻設定
    public void setStartTime(final int userId) {
        gameMapper.setStartTime(userId);
    }

    // ゲーム開始時刻取得
    public Timestamp getStartTime(final int roomId) {
        return gameMapper.getStartTime(roomId);
    }

    // 対戦相手ユーザー名取得
    public String getOpponentName(final int userId, final int roomId) {
        return gameMapper.getOpponentName(userId, roomId);
    }

    // スコア取得
    public short[] getScores(final int userId, final int roomId) {
        return gameMapper.getScores(userId, roomId);
    }

}
