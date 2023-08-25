package com.example.procon34_CYBER_WARS_backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.procon34_CYBER_WARS_backend.entity.Rooms;
import com.example.procon34_CYBER_WARS_backend.entity.Users;

@Mapper
public interface UtilityMapper {

    // ユーザー取得 by ユーザー名
    @Select("""
            SELECT
                *
            FROM
                users
            WHERE
                name = #{name}
            """)
    Users getUserByName(final Users user);

    // 未開始ルーム取得
    @Select("""
            SELECT
                *
            FROM
                rooms
            WHERE
                started = FALSE
            """)
    List<Rooms> getNotStartedRooms();

    // ルーム取得 by 招待ID
    @Select("""
            SELECT
                *
            FROM
                rooms
            WHERE
                invite_id = #{invite_id}
                AND started = FALSE
            """)
    Rooms getRoomByInviteId(final Rooms room);

}
