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

    // ルーム取得
    public Rooms getRoom(final short inviteId) {
        return roomMapper.getRoom(inviteId);
    }

    // ルーム参加
    public void join(final int userId, final short inviteId) {
        roomMapper.join(userId, inviteId);
    }

    // ルームID取得
    public int getRoomId(final int userId) {
        return roomMapper.getRoomId(userId);
    }

    // ルーム動作判定
    public boolean isActive(final int roomId) {
        return roomMapper.isActive(roomId);
    }

    // ルームホスト判定
    public boolean isHost(final int userId) {
        return roomMapper.isHost(userId);
    }

    // ルーム閉鎖
    public void close(final int roomId) {
        roomMapper.close(roomId);
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
