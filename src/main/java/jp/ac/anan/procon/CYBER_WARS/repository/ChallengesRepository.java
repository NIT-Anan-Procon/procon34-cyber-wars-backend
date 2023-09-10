package jp.ac.anan.procon.CYBER_WARS.repository;

import java.util.List;
import jp.ac.anan.procon.CYBER_WARS.entity.Challenges;
import jp.ac.anan.procon.CYBER_WARS.mapper.ChallengesMapper;
import jp.ac.anan.procon.CYBER_WARS.pojo.Vulnerability;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChallengesRepository {

  private final ChallengesMapper challengesMapper;

  // 脆弱性取得 by フラグ
  public Challenges fetchVulnerabilityByFlag(final int challengeId, final String flag) {
    return challengesMapper.fetchVulnerabilityByFlag(challengeId, flag);
  }

  // 脆弱性取得
  public List<Vulnerability> fetchVulnerabilities(final int challengeId) {
    return challengesMapper.fetchVulnerabilities(challengeId);
  }
}
