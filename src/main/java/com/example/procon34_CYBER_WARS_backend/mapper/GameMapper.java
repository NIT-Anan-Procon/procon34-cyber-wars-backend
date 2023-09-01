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

    // 対戦相手ユーザー名取得
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

    // スコア取得
    @Select("""
            SELECT
                sum_score
            FROM (
                SELECT
                    user_id, COALESCE(SUM(score), 0) AS sum_score
                FROM
                    games
                NATURAL JOIN
                    scores
                WHERE
                    room_id = #{roomId}
                GROUP BY
                    user_id
                ORDER BY
                    CASE
                        user_id
                    WHEN
                        #{userId} THEN 1
                    ELSE
                        2
                    END
            ) AS
                sum_scores
            """)
    short[] getScores(final int userId, final int roomId);

}
