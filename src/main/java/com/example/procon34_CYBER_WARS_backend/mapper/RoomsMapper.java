package com.example.procon34_CYBER_WARS_backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.example.procon34_CYBER_WARS_backend.entity.Allocations;
import com.example.procon34_CYBER_WARS_backend.entity.Rooms;
import com.example.procon34_CYBER_WARS_backend.entity.Users;
import com.example.procon34_CYBER_WARS_backend.entity.Vulnerabilities;

@Mapper
public interface RoomsMapper {

    // ルーム作成
    @Insert("""
            INSERT INTO
                rooms(invite_id, challenge_id)
            SELECT
                #{room.invite_id}, challenge_id
            FROM
                vulnerabilities
            WHERE
                difficult = #{vulnerability.difficult}
            ORDER BY
                RAND()
            LIMIT
                1
            """)
    void createRoom(final Rooms room, final Vulnerabilities vulnerability);

    // ルーム割り当て
    @Insert("""
            INSERT INTO
                allocations(room_id, user_id)
            SELECT
                MAX(room_id), #{user_id}
            FROM
                rooms
            """)
    void allocateRoom(final Allocations allocation);

    // ルーム参加
    @Insert("""
            INSERT INTO
                allocations(room.room_id, user_id)
            SELECT
                room_id, #{user_id}
            FROM
                rooms
            WHERE
                invite_id = #{room.invite_id}
                AND status = 0
            """)
    void joinRoom(final Rooms room, final Allocations allocation);

    // ルーム情報取得
    @Insert("""
            SELECT
                NOT host, name
            FROM
                allocations
            NATURAL JOIN
                users
            WHERE
                room_id = (
                    SELECT
                        room_id
                    FROM
                        allocations
                    WHERE
                        user_id = #{user_id}
                ) AND user_id != #{user_id};
            """)
    List<Rooms> getRoomInformation(final Users user, final Allocations allocation);

    // ルーム退出
    @Insert("""
            DELETE FROM
                allocations
            WHERE
                user_id = #{user_id}
            """)
    void leaveRoom(final Allocations allocation);

}
