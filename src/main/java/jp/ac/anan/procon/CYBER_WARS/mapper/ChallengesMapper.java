package jp.ac.anan.procon.CYBER_WARS.mapper;

import java.util.List;
import jp.ac.anan.procon.CYBER_WARS.entity.Challenges;
import jp.ac.anan.procon.CYBER_WARS.pojo.Vulnerability;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ChallengesMapper {

  // 脆弱性取得 by フラグ
  @Select(
      """
            SELECT
                *
            FROM
                challenges
            WHERE
                challenge_id = #{challengeId}
            AND
                flag = #{flag}
            """)
  @Results(
      id = "Challenges",
      value = {
        @Result(column = "challenge_id", property = "challengeId"),
        @Result(column = "vulnerability_id", property = "vulnerabilityId"),
        @Result(column = "choice", property = "choice"),
        @Result(column = "hint", property = "hint"),
        @Result(column = "flag", property = "flag"),
        @Result(column = "difficult", property = "difficult")
      })
  Challenges fetchVulnerabilityByFlag(final int challengeId, final String flag);

  // 脆弱性取得
  @Select(
      """
            SELECT
                choice, hint, score
            FROM
                challenges
            NATURAL JOIN
                scores
            WHERE
                challenge_id = #{challengeId}
            AND
                score_type = 1
            """)
  @Results(
      id = "Vulnerability",
      value = {
        @Result(column = "choice", property = "choice"),
        @Result(column = "hint", property = "hint"),
        @Result(column = "score", property = "hintScore")
      })
  List<Vulnerability> fetchVulnerabilities(final int challengeId);
}
