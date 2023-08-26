package com.example.procon34_CYBER_WARS_backend.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.procon34_CYBER_WARS_backend.entity.Rooms;
import com.example.procon34_CYBER_WARS_backend.mapper.RoomMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RoomRepository {

    private final RoomMapper roomMapper;

    // ルーム作成
    public void create(final short inviteId, final boolean difficult) {
        roomMapper.create(inviteId, difficult);
    }

    // ルーム割り当て
    public void allocate(final int userId) {
        roomMapper.allocate(userId);
    }

    // ルーム取得 by 招待ID
    public Rooms getRoomByInviteId(final short inviteId) {
        return roomMapper.getRoomByInviteId(inviteId);
    }

    // ルーム参加
    public void join(final int userId, final short inviteId) {
        roomMapper.join(userId, inviteId);
    }

    // 対戦相手ユーザー名取得
    public String getOpponentName(final int userId) {
        return roomMapper.getOpponentName(userId);
    }

    // ルーム動作判定
    public boolean isActive(final int userId) {
        return roomMapper.isActive(userId);
    }

    // ルームホスト判定
    public boolean isHost(final int userId) {
        return roomMapper.isHost(userId);
    }

    // ルーム閉鎖
    public void close(final int userId) {
        roomMapper.close(userId);
    }

    // ルーム退出
    public void exit(final int userId) {
        roomMapper.exit(userId);
    }

    // 動作ルーム取得
    public List<Rooms> getActiveRooms() {
        return roomMapper.getActiveRooms();
    }

}