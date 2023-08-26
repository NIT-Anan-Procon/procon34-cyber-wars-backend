package com.example.procon34_CYBER_WARS_backend.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.procon34_CYBER_WARS_backend.dto.room.GetInformationResponse;
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

    // ルーム参加
    public void join(final int userId, final short inviteId) {
        roomMapper.join(userId, inviteId);
    }

    // ルーム情報取得
    public GetInformationResponse getInformation(final int userId) {
        return roomMapper.getInformation(userId);
    }

    // ルーム退出
    public void leave(final int userId) {
        roomMapper.leave(userId);
    }

    // 未開始ルーム取得
    public List<Rooms> getNotStartedRooms() {
        return roomMapper.getNotStartedRooms();
    }

    // ルーム取得 by 招待ID
    public Rooms getRoomByInviteId(final short inviteId) {
        return roomMapper.getRoomByInviteId(inviteId);
    }

}
