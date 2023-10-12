package jp.ac.anan.procon.cyber_wars.application.service;

import static jp.ac.anan.procon.cyber_wars.application.Constant.PHP_DIRECTORY_PATH;

import jakarta.servlet.http.HttpServletRequest;
import java.nio.file.Files;
import java.nio.file.Paths;
import jp.ac.anan.procon.cyber_wars.application.utility.UserIdFetcher;
import jp.ac.anan.procon.cyber_wars.domain.dto.game.attack.FetchChallengeResponse;
import jp.ac.anan.procon.cyber_wars.domain.dto.game.attack.SendKeyRequest;
import jp.ac.anan.procon.cyber_wars.domain.dto.game.attack.SendKeyResponse;
import jp.ac.anan.procon.cyber_wars.domain.entity.Challenges;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.challenge.TableRepository;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.cyber_wars.AllocationsRepository;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.cyber_wars.ChallengesRepository;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.cyber_wars.GamesRepository;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.cyber_wars.RoomsRepository;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.cyber_wars.ScoresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class GameAttackService {
  private final RoomsRepository roomsRepository;
  private final AllocationsRepository allocationsRepository;
  private final GamesRepository gamesRepository;
  private final ChallengesRepository challengesRepository;
  private final ScoresRepository scoresRepository;
  private final TableRepository tableRepository;
  private final UserIdFetcher userIdFetcher;

  // 課題取得
  public FetchChallengeResponse fetchChallenge(final HttpServletRequest httpServletRequest) {
    final int roomId = allocationsRepository.fetchRoomId(userIdFetcher.fetch(httpServletRequest));
    final int challengeId = roomsRepository.fetchChallengeId(roomId);
    String targetCode;

    try {
      targetCode =
          Files.readString(Paths.get(PHP_DIRECTORY_PATH + "game/" + roomId + "/target/index.php"));
    } catch (final Exception exception) {
      exception.printStackTrace();

      return new FetchChallengeResponse(roomId, null, null, null, null, null);
    }

    final Challenges challenges = challengesRepository.fetchChallenge(challengeId);

    return new FetchChallengeResponse(
        roomId,
        targetCode,
        challenges.getGoal(),
        challenges.getChoices().split(","),
        challenges.getHint(),
        scoresRepository.fetchScore((byte) 1));
  }

  // ヒント使用
  public void useHint(final HttpServletRequest httpServletRequest) {
    final int userId = userIdFetcher.fetch(httpServletRequest);
    final int roomId = allocationsRepository.fetchRoomId(userId);

    gamesRepository.addScore(userId, roomId, roomsRepository.fetchChallengeId(roomId), (byte) 1);
  }

  // フラグ送信
  public SendKeyResponse sendKey(
      final SendKeyRequest sendKeyRequest, final HttpServletRequest httpServletRequest) {
    final int userId = userIdFetcher.fetch(httpServletRequest);
    final int roomId = allocationsRepository.fetchRoomId(userId);
    final int challengeId = roomsRepository.fetchChallengeId(roomId);
    String key = sendKeyRequest.key();

    // フラグにKEY{が含まれない場合
    if (!key.contains("KEY{")) {
      key = "KEY{" + key + "}";
    }

    // レコードが存在しない場合
    if (tableRepository.fetchRecord(
            challengesRepository.fetchTargetTable(roomsRepository.fetchChallengeId(roomId))
                + roomId,
            key)
        == null) {
      return new SendKeyResponse(null, false, null);
    }

    // ゲームが存在する場合
    if (gamesRepository.fetchGame(userId, roomId, challengeId, (byte) 0) != null) {
      return new SendKeyResponse(false, true, null);
    }

    gamesRepository.addScore(userId, roomId, challengeId, (byte) 0);

    return new SendKeyResponse(true, true, scoresRepository.fetchScore((byte) 0));
  }
}
