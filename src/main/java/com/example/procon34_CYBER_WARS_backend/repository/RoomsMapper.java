package com.example.procon34_CYBER_WARS_backend.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.example.procon34_CYBER_WARS_backend.dto.rooms.CreateRoomRequest;

@Mapper
public interface RoomsMapper {

    // ルーム作成
    @Insert("INSERT INTO rooms(invite_id, challenge_id) VALUES(#{invite_id}, SELECT challenge_id FROM vulnerabilities ORDER BY RAND() LIMIT 1)")
    void createRoom(CreateRoomRequest createRoomRequest);

    // ルーム割り当て
    @Insert("INSERT INTO allocations(room_id, user_id) VALUES(SELECT MAX(room_id) FROM rooms,#{user_id})")
    void allocateRoom(CreateRoomRequest createRoomRequest);

}
