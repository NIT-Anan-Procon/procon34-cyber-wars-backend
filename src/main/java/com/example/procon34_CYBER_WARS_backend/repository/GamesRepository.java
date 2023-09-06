package com.example.procon34_CYBER_WARS_backend.repository;

import org.springframework.stereotype.Repository;

import com.example.procon34_CYBER_WARS_backend.mapper.GamesMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class GamesRepository {

    private final GamesMapper gamesMapper;

    // 自分スコア取得
    public short fetchMyScore(final int userId, final int roomId) {
        return gamesMapper.fetchMyScore(userId, roomId);
    }

    // 相手スコア取得
    public short fetchOpponentScore(final int userId, final int roomId) {
        return gamesMapper.fetchOpponentScore(userId, roomId);
    }

}
