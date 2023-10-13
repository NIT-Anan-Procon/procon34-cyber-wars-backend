package jp.ac.anan.procon.cyber_wars.application.utility;

import jakarta.servlet.http.HttpServletRequest;
import jp.ac.anan.procon.cyber_wars.domain.dto.utility.SendResponse;
import jp.ac.anan.procon.cyber_wars.domain.pojo.TimeLimit;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.challenge.TableRepository;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.cyber_wars.AllocationsRepository;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.cyber_wars.ChallengesRepository;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.cyber_wars.GamesRepository;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.cyber_wars.RoomsRepository;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.cyber_wars.ScoresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KeySender {
  private final RoomsRepository roomsRepository;
  private final AllocationsRepository allocationsRepository;
  private final GamesRepository gamesRepository;
  private final ChallengesRepository challengesRepository;
  private final ScoresRepository scoresRepository;
  private final TableRepository tableRepository;
  private final UserIdFetcher userIdFetcher;

  // キー送信
  public SendResponse send(
      String key, final String phase, final HttpServletRequest httpServletRequest) {
    final int userId = userIdFetcher.fetch(httpServletRequest);
    final int roomId = allocationsRepository.fetchRoomId(userId);
    final int challengeId = roomsRepository.fetchChallengeId(roomId);

    // キーにFLAG{が含まれない場合
    if (!key.contains("KEY{")) {
      key = "KEY{" + key + "}";
    }

    // レコードが存在しない場合
    if (tableRepository.fetchRecord(
            challengesRepository.fetchTargetTable(roomsRepository.fetchChallengeId(roomId))
                + roomId,
            key)
        == null) {
      return new SendResponse(null, false, null);
    }

    byte gameId = 1;
    short timeOffset = 0;
    final TimeLimit timeLimit = roomsRepository.fetchTimeLimit(roomId);

    // バトルフェーズである場合
    if (phase.equals("battle")) {
      gameId = 5;
      timeOffset = (short) (timeLimit.attackPhaseTimeLimit() + timeLimit.defencePhaseTimeLimit());
    }

    // ゲームが存在する場合
    if (gamesRepository.fetchGame(userId, roomId, challengeId, gameId) != null) {
      return new SendResponse(false, true, null);
    }

    gamesRepository.addScore(
        userId,
        roomId,
        challengeId,
        gameId,
        roomsRepository.fetchScoreMultiplier(roomId, phase, timeOffset));

    return new SendResponse(true, true, scoresRepository.fetchScore(gameId));
  }
}
