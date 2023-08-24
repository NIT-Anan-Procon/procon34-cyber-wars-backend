package com.example.procon34_CYBER_WARS_backend.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.example.procon34_CYBER_WARS_backend.entity.Allocations;
import com.example.procon34_CYBER_WARS_backend.entity.Rooms;
import com.example.procon34_CYBER_WARS_backend.entity.Vulnerabilities;

@Mapper
public interface RoomsMapper {

    // ルーム作成
    @Insert("INSERT INTO rooms(invite_id, challenge_id) SELECT #{rooms.invite_id}, challenge_id FROM vulnerabilities WHERE difficult = #{vulnerabilities.difficult} ORDER BY RAND() LIMIT 1")
    void createRoom(final Rooms room, final Vulnerabilities vulnerability);

    // ルーム割り当て
    @Insert("INSERT INTO allocations(room_id, user_id) SELECT MAX(room_id), #{user_id} FROM rooms")
    void allocateRoom(final Allocations allocation);

}
