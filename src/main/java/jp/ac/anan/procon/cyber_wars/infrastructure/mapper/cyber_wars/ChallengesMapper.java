package jp.ac.anan.procon.cyber_wars.infrastructure.mapper.cyber_wars;

import jp.ac.anan.procon.cyber_wars.domain.entity.Challenges;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ChallengesMapper {
  // 課題取得
  @Select(
      """
      SELECT
        *
      FROM
        challenges
      WHERE
        challenge_id = #{challengeId}
      """)
  @Results(
      id = "Challenges",
      value = {
        @Result(column = "challenge_id", property = "challengeId"),
        @Result(column = "goal", property = "goal"),
        @Result(column = "choice", property = "choice"),
        @Result(column = "hint", property = "hint"),
        @Result(column = "explanation", property = "explanation"),
        @Result(column = "target_table", property = "targetTable")
      })
  Challenges fetchChallenge(final int challengeId);

  // 解説取得
  @Select(
      """
      SELECT
        explanation
      FROM
        challenges
      WHERE
        challenge_id = #{challengeId}
      """)
  String fetchExplanation(final int challengeId);

  // 標的テーブル取得
  @Select(
      """
      SELECT
        target_table
      FROM
        challenges
      WHERE
        challenge_id = #{challengeId}
      """)
  String fetchTargetTable(final int challengeId);

  // 全課題有効化
  @Update("""
      UPDATE
        challenges
      SET
        available = TRUE
      """)
  void enableAllChallenges();

  // 全課題無効化
  @Update("""
      UPDATE
        challenges
      SET
        available = FALSE
      """)
  void disableAllChallenges();

  // 課題有効化
  @Update(
      """
      UPDATE
        challenges
      SET
        available = TRUE
      WHERE
        challenge_id
      IN
        (${challengeIds})
      """)
  void enableChallenges(final String challengeIds);
}
