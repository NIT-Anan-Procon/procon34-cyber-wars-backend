package com.example.procon34_CYBER_WARS_backend.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.procon34_CYBER_WARS_backend.dto.rooms.GetInformationResponse;
import com.example.procon34_CYBER_WARS_backend.entity.Rooms;
import com.example.procon34_CYBER_WARS_backend.mapper.RoomsMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RoomsRepository {

    private final RoomsMapper roomsMapper;

    // ルーム作成
    public void create(final short inviteId, final boolean difficult) {
        roomsMapper.create(inviteId, difficult);
    }

    // ルーム割り当て
    public void allocate(final int userId) {
        roomsMapper.allocate(userId);
    }

    // ルーム参加
    public void join(final int userId, final short inviteId) {
        roomsMapper.join(userId, inviteId);
    }

    // ルーム情報取得
    public GetInformationResponse getInformation(final int userId) {
        return roomsMapper.getInformation(userId);
    }

    // ルーム退出
    public void leave(final int userId) {
        roomsMapper.leave(userId);
    }

    // 未開始ルーム取得
    public List<Rooms> getNotStartedRooms() {
        return roomsMapper.getNotStartedRooms();
    }

    // ルーム取得 by 招待ID
    public Rooms getRoomByInviteId(final short inviteId) {
        return roomsMapper.getRoomByInviteId(inviteId);
    }

}
