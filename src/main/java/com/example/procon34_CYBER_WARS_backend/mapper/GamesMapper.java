package com.example.procon34_CYBER_WARS_backend.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.procon34_CYBER_WARS_backend.entity.Games;

@Mapper
public interface GamesMapper {

    // スコア追加
    @Insert("""
            INSERT INTO
                games(room_id, user_id, vulnerability_id, score_type)
            VALUES
                (#{roomId}, #{userId}, #{vulnerabilityId}, #{scoreType})
            """)
    void addScore(final int userId, final int roomId, final byte vulnerabilityId, final byte scoreType);

    // ゲーム取得
    @Select("""
            SELECT
                *
            FROM
                games
            WHERE
                room_id = #{roomId}
            AND
                user_id = #{userId}
            AND
                vulnerability_id = #{vulnerabilityId}
            AND
                score_type = #{scoreType}
            """)
    @Results(id = "Games", value = {
            @Result(column = "room_id", property = "roomId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "vulnerability_id", property = "vulnerabilityId"),
            @Result(column = "score_type", property = "scoreType")
    })
    Games fetchGame(final int userId, final int roomId, final byte vulnerabilityId, final byte scoreType);

    // スコア取得
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
                CASE
                    self
                WHEN TRUE THEN
                    user_id = #{userId}
                ELSE
                    user_id != #{userId}
            """)
    short fetchScore(final int userId, final int roomId, final boolean self);

}
