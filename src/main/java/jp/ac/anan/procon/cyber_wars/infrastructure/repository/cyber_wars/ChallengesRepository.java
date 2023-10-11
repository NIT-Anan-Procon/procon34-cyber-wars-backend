package jp.ac.anan.procon.cyber_wars.infrastructure.repository.cyber_wars;

import jp.ac.anan.procon.cyber_wars.domain.entity.Challenges;
import jp.ac.anan.procon.cyber_wars.infrastructure.mapper.cyber_wars.ChallengesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ChallengesRepository {
  private final ChallengesMapper challengesMapper;

  // 課題取得 by 課題ID
  public Challenges fetchChallenge(final int challengeId) {
    return challengesMapper.fetchChallenge(challengeId);
  }

  // 解説取得
  public String fetchExplanation(final int challengeId) {
    return challengesMapper.fetchExplanation(challengeId);
  }

  // 標的テーブル取得
  public String fetchTargetTable(final int challengeId) {
    return challengesMapper.fetchTargetTable(challengeId);
  }

  // 全課題有効化
  public void enableAllChallenges() {
    challengesMapper.enableAllChallenges();
  }

  // 全課題無効化
  public void disableAllChallenges() {
    challengesMapper.disableAllChallenges();
  }

  // 課題有効化
  public void enableChallenges(final String challengeIds) {
    challengesMapper.enableChallenges(challengeIds);
  }
}
