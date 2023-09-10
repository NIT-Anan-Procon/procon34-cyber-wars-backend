package jp.ac.anan.procon.cyber_wars.infrastructure.repository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import jp.ac.anan.procon.cyber_wars.domain.entity.Challenges;
import jp.ac.anan.procon.cyber_wars.domain.pojo.Vulnerability;
import jp.ac.anan.procon.cyber_wars.infrastructure.mapper.ChallengesMapper;

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
