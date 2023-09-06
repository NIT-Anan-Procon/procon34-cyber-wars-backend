package com.example.procon34_CYBER_WARS_backend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GamesMapper {

    // 自分スコア取得
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
                user_id = #{userId}
            """)
    short fetchMyScore(final int userId, final int roomId);

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
    short fetchOpponentScore(final int userId, final int roomId);

}
