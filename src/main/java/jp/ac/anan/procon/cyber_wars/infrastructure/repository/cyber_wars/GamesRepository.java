package jp.ac.anan.procon.cyber_wars.infrastructure.repository.cyber_wars;

import jp.ac.anan.procon.cyber_wars.domain.entity.Games;
import jp.ac.anan.procon.cyber_wars.infrastructure.mapper.cyber_wars.GamesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GamesRepository {
  private final GamesMapper gamesMapper;

  // スコア追加
  public void addScore(
      final int userId, final int roomId, final int challengeId, final byte scoreType) {
    gamesMapper.addScore(userId, roomId, challengeId, scoreType);
  }

  // ゲーム取得
  public Games fetchGame(
      final int userId, final int roomId, final int challengeId, final byte scoreType) {
    return gamesMapper.fetchGame(userId, roomId, challengeId, scoreType);
  }

  // スコア取得
  public short fetchScore(
      final int userId, final int roomId, final int challengeId, final boolean self) {
    return gamesMapper.fetchScore(userId, roomId, challengeId, self);
  }
}
