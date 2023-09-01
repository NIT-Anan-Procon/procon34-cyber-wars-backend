package com.example.procon34_CYBER_WARS_backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
                challenges
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

    // ルーム取得
    @Select("""
            SELECT
                *
            FROM
                rooms
            WHERE
                invite_id = #{inviteId}
            AND
                active = TRUE
            """)
    @Results(id = "Rooms", value = {
            @Result(column = "room_id", property = "roomId"),
            @Result(column = "invite_id", property = "inviteId"),
            @Result(column = "challenge_id", property = "challengeId"),
            @Result(column = "started_at", property = "startedAt"),
            @Result(column = "active", property = "active")
    })
    Rooms getRoom(final short inviteId);

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
            AND
                active = TRUE
            """)
    void join(final int userId, final short inviteId);

    // ルームID取得
    @Select("""
            SELECT
                room_id
            FROM
                allocations
            WHERE
                user_id = #{userId}
            """)
    int getRoomId(final int userId);

    // ルーム動作判定
    @Select("""
            SELECT
                active
            FROM
                rooms
            WHERE
                room_id = #{roomId}
            """)
    boolean isActive(final int roomId);

    // ルームホスト判定
    @Select("""
            SELECT
                host
            FROM
                allocations
            WHERE
                user_id = #{userId}
            """)
    boolean isHost(final int userId);

    // ルーム閉鎖
    @Update("""
            UPDATE
                rooms
            SET
                active = FALSE
            WHERE
                room_id = #{roomId}
            """)
    void close(final int roomId);

    // ルーム退出
    @Delete("""
            DELETE FROM
                allocations
            WHERE
                user_id = #{userId}
            """)
    void exit(final int userId);

    // 動作ルーム取得
    @Select("""
            SELECT
                *
            FROM
                rooms
            WHERE
                active = TRUE
            """)
    @ResultMap("Rooms")
    List<Rooms> getActiveRooms();

}
