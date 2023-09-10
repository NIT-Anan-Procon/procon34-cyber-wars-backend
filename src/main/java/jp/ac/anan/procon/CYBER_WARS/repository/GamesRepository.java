package jp.ac.anan.procon.CYBER_WARS.repository;

import jp.ac.anan.procon.CYBER_WARS.entity.Games;
import jp.ac.anan.procon.CYBER_WARS.mapper.GamesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GamesRepository {

  private final GamesMapper gamesMapper;

  // スコア追加
  public void addScore(
      final int userId, final int roomId, final byte vulnerabilityId, final byte scoreType) {
    gamesMapper.addScore(userId, roomId, vulnerabilityId, scoreType);
  }

  // ゲーム取得
  public Games fetchGame(
      final int userId, final int roomId, final byte vulnerabilityId, final byte scoreType) {
    return gamesMapper.fetchGame(userId, roomId, vulnerabilityId, scoreType);
  }

  // スコア取得
  public short fetchScore(final int userId, final int roomId, final boolean self) {
    return gamesMapper.fetchScore(userId, roomId, self);
  }
}
