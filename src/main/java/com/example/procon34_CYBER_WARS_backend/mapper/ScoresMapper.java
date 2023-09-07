package com.example.procon34_CYBER_WARS_backend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ScoresMapper {

    // スコア取得
    @Select("""
            SELECT
                score
            FROM
                scores
            WHERE
                score_type = #{scoreType}
            AND
                difficult = #{difficult}
            """)
    byte fetchScore(final byte scoreType, final boolean difficult);

}
