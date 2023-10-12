package jp.ac.anan.procon.cyber_wars.infrastructure.mapper.cyber_wars;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ScoresMapper {
  // スコア取得
  @Select(
      """
      SELECT
        score
      FROM
        scores
      WHERE
        game_id = #{gameId}
      """)
  short fetchScore(final byte gameId);
}
