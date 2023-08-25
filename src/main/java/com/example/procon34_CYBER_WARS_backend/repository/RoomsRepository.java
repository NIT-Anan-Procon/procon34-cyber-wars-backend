package com.example.procon34_CYBER_WARS_backend.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.procon34_CYBER_WARS_backend.dto.rooms.GetRoomInformationResponse;
import com.example.procon34_CYBER_WARS_backend.entity.Rooms;
import com.example.procon34_CYBER_WARS_backend.mapper.RoomsMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RoomsRepository {

    private final RoomsMapper roomsMapper;

    // ルーム作成
    public void createRoom(final short inviteId, final boolean difficult) {
        roomsMapper.createRoom(inviteId, difficult);
    }

    // ルーム割り当て
    public void allocateRoom(final int userId) {
        roomsMapper.allocateRoom(userId);
    }

    // ルーム参加
    public void joinRoom(final int userId, final short inviteId) {
        roomsMapper.joinRoom(userId, inviteId);
    }

    // ルーム情報取得
    public GetRoomInformationResponse getRoomInformation(final int userId) {
        return roomsMapper.getRoomInformation(userId);
    }

    // ルーム退出
    public void leaveRoom(final int userId) {
        roomsMapper.leaveRoom(userId);
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
