package jp.ac.anan.procon.cyber_wars.infrastructure.repository.cyber_wars;

import jp.ac.anan.procon.cyber_wars.infrastructure.mapper.cyber_wars.ScoresMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ScoresRepository {
  private final ScoresMapper scoresMapper;

  // スコア取得
  public short fetchScore(final byte gameId) {
    return scoresMapper.fetchScore(gameId);
  }
}
