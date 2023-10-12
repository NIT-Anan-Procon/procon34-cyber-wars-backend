package jp.ac.anan.procon.cyber_wars.application.service;

import static jp.ac.anan.procon.cyber_wars.application.Constant.PHP_DIRECTORY_PATH;

import jakarta.servlet.http.HttpServletRequest;
import java.nio.file.Files;
import java.nio.file.Paths;
import jp.ac.anan.procon.cyber_wars.application.utility.TableUtility;
import jp.ac.anan.procon.cyber_wars.application.utility.UserIdFetcher;
import jp.ac.anan.procon.cyber_wars.domain.dto.game.battle.FetchRevisionResponse;
import jp.ac.anan.procon.cyber_wars.domain.dto.game.battle.SendKeyRequest;
import jp.ac.anan.procon.cyber_wars.domain.dto.game.battle.SendKeyResponse;
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
public class GameBattleService {
  private final RoomsRepository roomsRepository;
  private final AllocationsRepository allocationsRepository;
  private final GamesRepository gamesRepository;
  private final ChallengesRepository challengesRepository;
  private final ScoresRepository scoresRepository;
  private final TableRepository tableRepository;
  private final UserIdFetcher userIdFetcher;
  private final TableUtility tableUtility;

  // バトルフェーズ：修正課題取得
  public FetchRevisionResponse fetchRevision(final HttpServletRequest httpServletRequest) {
    final int userId = userIdFetcher.fetch(httpServletRequest);
    final int roomId = allocationsRepository.fetchRoomId(userId);

    // ユーザーがホストである場合
    if (allocationsRepository.isHost(userId)) {
      tableRepository.updateKey(
          challengesRepository.fetchTargetTable(roomsRepository.fetchChallengeId(roomId)) + roomId,
          tableUtility.generateKey(),
          tableUtility.generateId(
              challengesRepository.fetchTargetTable(roomsRepository.fetchChallengeId(roomId))));

      roomsRepository.open(roomId);
    }

    final int opponentUserId = allocationsRepository.fetchOpponentUserId(userId, roomId);
    String revisionCode;

    try {
      revisionCode =
          Files.readString(
              Paths.get(
                  PHP_DIRECTORY_PATH + "game/" + roomId + "/revision/" + opponentUserId + ".php"));
    } catch (final Exception exception) {
      exception.printStackTrace();

      return new FetchRevisionResponse(opponentUserId, null);
    }

    return new FetchRevisionResponse(opponentUserId, revisionCode);
  }

  // バトルフェーズ：キー送信
  public SendKeyResponse sendKey(
      final SendKeyRequest sendKeyRequest, final HttpServletRequest httpServletRequest) {
    final int userId = userIdFetcher.fetch(httpServletRequest);
    final int roomId = allocationsRepository.fetchRoomId(userId);
    final int challengeId = roomsRepository.fetchChallengeId(roomId);
    String key = sendKeyRequest.key();

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
      return new SendKeyResponse(null, false, null);
    }

    // ゲームが存在する場合
    if (gamesRepository.fetchGame(userId, roomId, challengeId, (byte) 5) != null) {
      return new SendKeyResponse(false, true, null);
    }

    gamesRepository.addScore(userId, roomId, challengeId, (byte) 5);

    return new SendKeyResponse(true, true, scoresRepository.fetchScore((byte) 5));
  }
}
