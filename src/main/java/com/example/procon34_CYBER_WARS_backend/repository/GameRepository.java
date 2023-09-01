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
    public void setStartTime(final int roomId) {
        gameMapper.setStartTime(roomId);
    }

    // ゲーム開始時刻取得
    public Timestamp getStartTime(final int roomId) {
        return gameMapper.getStartTime(roomId);
    }

    // 相手ユーザー名取得
    public String getOpponentName(final int userId, final int roomId) {
        return gameMapper.getOpponentName(userId, roomId);
    }

    // 自分スコア取得
    public short getMyScore(final int userId, final int roomId) {
        return gameMapper.getMyScore(userId, roomId);
    }

    // 相手スコア取得
    public short getOpponentScore(final int userId, final int roomId) {
        return gameMapper.getOpponentScore(userId, roomId);
    }

}
