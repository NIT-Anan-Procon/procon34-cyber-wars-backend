package jp.ac.anan.procon.cyber_wars.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import jp.ac.anan.procon.cyber_wars.infrastructure.mapper.ScoresMapper;

@Repository
@RequiredArgsConstructor
public class ScoresRepository {

  private final ScoresMapper scoresMapper;

  // スコア取得
  public byte fetchScore(final byte scoreType, final boolean difficult) {
    return scoresMapper.fetchScore(scoreType, difficult);
  }
}
