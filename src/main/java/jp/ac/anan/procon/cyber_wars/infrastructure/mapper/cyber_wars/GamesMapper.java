package jp.ac.anan.procon.cyber_wars.infrastructure.mapper.cyber_wars;

import jp.ac.anan.procon.cyber_wars.domain.entity.Games;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GamesMapper {
  // スコア追加
  @Insert(
      """
      INSERT INTO
        games(room_id,challenge_id, user_id, score_type)
      VALUES
        (#{roomId},#{challengeId}, #{userId}, #{scoreType})
      """)
  void addScore(final int userId, final int challengeId, final int roomId, final byte scoreType);

  // ゲーム取得
  @Select(
      """
      SELECT
        *
      FROM
        games
      WHERE
        room_id = #{roomId}
      AND
        challenge_id = #{challengeId}
      AND
        user_id = #{userId}
      AND
        score_type = #{scoreType}
      """)
  @Results(
      id = "Games",
      value = {
        @Result(column = "room_id", property = "roomId"),
        @Result(column = "challenge_id", property = "challengeId"),
        @Result(column = "user_id", property = "userId"),
        @Result(column = "score_type", property = "scoreType")
      })
  Games fetchGame(final int userId, final int roomId, final int challengeId, final byte scoreType);

  // スコア取得
  @Select(
      """
      SELECT
        COALESCE(SUM(score), 0)
      FROM
        games
      NATURAL JOIN
        scores
      WHERE
        room_id = #{roomId}
      AND
        CASE
          #{self}
        WHEN TRUE THEN
          user_id = #{userId}
        ELSE
          user_id != #{userId}
        END
      """)
  short fetchScore(final int userId, final int roomId, final int challengeId, final boolean self);
}
