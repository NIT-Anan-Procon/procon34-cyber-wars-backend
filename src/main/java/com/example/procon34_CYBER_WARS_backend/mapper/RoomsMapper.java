package com.example.procon34_CYBER_WARS_backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.procon34_CYBER_WARS_backend.dto.rooms.GetInformationResponse;
import com.example.procon34_CYBER_WARS_backend.entity.Rooms;

@Mapper
public interface RoomsMapper {

    // ルーム作成
    @Insert("""
            INSERT INTO
                rooms(invite_id, challenge_id)
            SELECT
                #{invite_id}, challenge_id
            FROM
                vulnerabilities
            WHERE
                difficult = #{difficult}
            ORDER BY
                RAND()
            LIMIT
                1
            """)
    void create(@Param("invite_id") final short inviteId, @Param("difficult") final boolean difficult);

    // ルーム割り当て
    @Insert("""
            INSERT INTO
                allocations(room_id, user_id)
            SELECT
                MAX(room_id), #{user_id}
            FROM
                rooms
            """)
    void allocate(@Param("user_id") final int userId);

    // ルーム参加
    @Insert("""
            INSERT INTO
                allocations(room_id, user_id, host)
            SELECT
                room_id, #{user_id}, FALSE
            FROM
                rooms
            WHERE
                invite_id = #{invite_id}
                AND status = 0
            """)
    void join(@Param("user_id") final int userId, @Param("invite_id") final short inviteId);

    // ルーム情報取得
    @Insert("""
            SELECT
                - host, name
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
                ) AND user_id != #{user_id}
            """)
    @Results({
            @Result(column = "- host", property = "host"),
            @Result(column = "name", property = "opponentName"),
    })
    GetInformationResponse getInformation(@Param("user_id") final int userId);

    // ルーム退出
    @Insert("""
            DELETE FROM
                allocations
            WHERE
                user_id = #{user_id}
            """)
    void leave(@Param("user_id") final int userId);

    // 未開始ルーム取得
    @Select("""
            SELECT
                *
            FROM
                rooms
            WHERE
                started = FALSE
            """)
    @Results(id = "Rooms", value = {
            @Result(column = "room_id", property = "roomId"),
            @Result(column = "invite_id", property = "inviteId"),
            @Result(column = "challenge_id", property = "challengeId"),
            @Result(column = "started_at", property = "startedAt"),
            @Result(column = "started", property = "started")
    })
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
    @ResultMap("Rooms")
    Rooms getRoomByInviteId(@Param("invite_id") final short inviteId);

}
