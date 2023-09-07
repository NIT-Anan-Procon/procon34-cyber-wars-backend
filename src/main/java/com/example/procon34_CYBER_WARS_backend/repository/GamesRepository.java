package com.example.procon34_CYBER_WARS_backend.repository;

import org.springframework.stereotype.Repository;

import com.example.procon34_CYBER_WARS_backend.entity.Games;
import com.example.procon34_CYBER_WARS_backend.mapper.GamesMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class GamesRepository {

    private final GamesMapper gamesMapper;

    // スコア追加
    public void addScore(final int userId, final int roomId, final byte vulnerabilityId, final byte scoreType) {
        gamesMapper.addScore(userId, roomId, vulnerabilityId, scoreType);
    }

    // ゲーム取得
    public Games fetchGame(final int userId, final int roomId, final byte vulnerabilityId, final byte scoreType) {
        return gamesMapper.fetchGame(userId, roomId, vulnerabilityId, scoreType);
    }

    // スコア取得
    public short fetchScore(final int userId, final int roomId, final boolean self) {
        return gamesMapper.fetchScore(userId, roomId, self);
    }

}
