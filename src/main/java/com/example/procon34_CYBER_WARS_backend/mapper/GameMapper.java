package com.example.procon34_CYBER_WARS_backend.mapper;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface GameMapper {

    // ゲーム開始時刻設定
    @Update("""
            UPDATE
                rooms
            SET
                start_time = CURRENT_TIMESTAMP()
            WHERE
                room_id = #{roomId}
            """)
    void setStartTime(final int roomId);

    // ゲーム開始時刻取得
    @Select("""
            SELECT
                start_time
            FROM
                rooms
            WHERE
                room_id = #{roomId}
            """)
    Timestamp getStartTime(final int roomId);

    // 相手ユーザー名取得
    @Select("""
            SELECT
                name
            FROM
                allocations
            NATURAL JOIN
                users
            WHERE
                room_id = #{roomId}
            AND
                user_id != #{userId}
            """)
    String getOpponentName(final int userId, final int roomId);

    // 自分スコア取得
    @Select("""
            SELECT
                COALESCE(SUM(score), 0)
            FROM
                games
            NATURAL JOIN
                scores
            WHERE
                room_id = #{roomId}
            AND
                user_id = #{userId}
            """)
    short getMyScore(final int userId, final int roomId);

    // 相手スコア取得
    @Select("""
            SELECT
                COALESCE(SUM(score), 0)
            FROM
                games
            NATURAL JOIN
                challenges
            NATURAL JOIN
                scores
            WHERE
                room_id = #{roomId}
            AND
                user_id != #{userId}
            """)
    short getOpponentScore(final int userId, final int roomId);

}
