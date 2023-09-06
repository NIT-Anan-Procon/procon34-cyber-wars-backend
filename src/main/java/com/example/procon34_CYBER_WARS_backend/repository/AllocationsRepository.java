package com.example.procon34_CYBER_WARS_backend.repository;

import org.springframework.stereotype.Repository;

import com.example.procon34_CYBER_WARS_backend.mapper.AllocationsMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AllocationsRepository {

    private final AllocationsMapper allocationsMapper;

    // ルーム参加
    public void join(final int userId, final short inviteId, final boolean host) {
        allocationsMapper.join(userId, inviteId, host);
    }

    // ルームID取得
    public int fetchRoomId(final int userId) {
        return allocationsMapper.fetchRoomId(userId);
    }

    // ルームホスト判定
    public boolean isHost(final int userId) {
        return allocationsMapper.isHost(userId);
    }

    // 相手ユーザー名取得
    public String fetchOpponentName(final int userId, final int roomId) {
        return allocationsMapper.fetchOpponentName(userId, roomId);
    }

    // ルーム退出
    public void exit(final int userId) {
        allocationsMapper.exit(userId);
    }

}
