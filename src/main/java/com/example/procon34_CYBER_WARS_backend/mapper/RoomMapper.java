package com.example.procon34_CYBER_WARS_backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.procon34_CYBER_WARS_backend.entity.Rooms;

@Mapper
public interface RoomMapper {

    // ルーム作成
    @Insert("""
            INSERT INTO
                rooms(invite_id, challenge_id)
            SELECT
                #{inviteId}, challenge_id
            FROM
                vulnerabilities
            WHERE
                difficult = #{difficult}
            ORDER BY
                RAND()
            LIMIT
                1
            """)
    void create(final short inviteId, final boolean difficult);

    // ルーム割り当て
    @Insert("""
            INSERT INTO
                allocations(room_id, user_id)
            SELECT
                MAX(room_id), #{userId}
            FROM
                rooms
            """)
    void allocate(final int userId);

    // ルーム取得 by 招待ID
    @Select("""
            SELECT
                *
            FROM
                rooms
            WHERE
                invite_id = #{inviteId}
                AND started = FALSE
            """)
    @Results(id = "Rooms", value = {
            @Result(column = "room_id", property = "roomId"),
            @Result(column = "invite_id", property = "inviteId"),
            @Result(column = "challenge_id", property = "challengeId"),
            @Result(column = "started_at", property = "startedAt"),
            @Result(column = "started", property = "started")
    })
    Rooms getRoomByInviteId(final short inviteId);

    // ルーム参加
    @Insert("""
            INSERT INTO
                allocations(room_id, user_id, host)
            SELECT
                room_id, #{userId}, FALSE
            FROM
                rooms
            WHERE
                invite_id = #{inviteId}
                AND started = FALSE
            """)
    void join(final int userId, final short inviteId);

    // ホスト判定
    @Select("""
            SELECT
                host
            FROM
                allocations
            WHERE
                user_id != #{userId}
            """)
    @Result(column = "host", property = "host")
    boolean isHost(final int userId);

    // 対戦相手ユーザー名取得
    @Select("""
            SELECT
                name
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
                        user_id = #{userId}
                ) AND user_id != #{userId}
            """)
    @Result(column = "name", property = "opponentName")
    String getOpponentName(final int userId);

    // ルーム退出
    @Delete("""
            DELETE FROM
                allocations
            WHERE
                user_id = #{userId}
            """)
    void leave(final int userId);

    // 未開始ルーム取得
    @Select("""
            SELECT
                *
            FROM
                rooms
            WHERE
                started = FALSE
            """)
    @ResultMap("Rooms")
    List<Rooms> getNotStartedRooms();

}
