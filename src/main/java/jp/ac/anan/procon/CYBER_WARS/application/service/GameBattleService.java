package jp.ac.anan.procon.cyber_wars.application.service;

import jakarta.servlet.http.HttpServletRequest;
import jp.ac.anan.procon.cyber_wars.application.utility.rooms.RoomIdFetcher;
import jp.ac.anan.procon.cyber_wars.application.utility.users.UserIdFetcher;
import jp.ac.anan.procon.cyber_wars.domain.dto.game.battle.FetchRevisionResponse;
import jp.ac.anan.procon.cyber_wars.domain.dto.game.battle.SendKeyRequest;
import jp.ac.anan.procon.cyber_wars.domain.dto.game.battle.SendKeyResponse;
import jp.ac.anan.procon.cyber_wars.domain.entity.Challenges;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.AllocationsRepository;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.ChallengesRepository;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.GamesRepository;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.RoomsRepository;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.ScoresRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
  private final UserIdFetcher userIdFetcher;
  private final RoomIdFetcher roomIdFetcher;

  // 修正課題取得
  public FetchRevisionResponse fetchRevision(final HttpServletRequest httpServletRequest) {
    final int userId = userIdFetcher.fetchUserId(httpServletRequest);
    final int roomId = roomIdFetcher.fetchRoomId(userId);
    final String path =
        "revision"
            + roomId
            + "-"
            + allocationsRepository.fetchOpponentUserId(userId, roomId)
            + ".php";

    try {
      return new FetchRevisionResponse(path, Files.readString(Paths.get(path)));
    } catch (final IOException exception) {
      return new FetchRevisionResponse(null, null);
    }
  }

  // キー送信
  public SendKeyResponse sendKey(
      final SendKeyRequest sendKeyRequest, final HttpServletRequest httpServletRequest) {
    final int userId = userIdFetcher.fetchUserId(httpServletRequest);
    final int roomId = roomIdFetcher.fetchRoomId(userId);
    final Challenges vulnerability =
        challengesRepository.fetchVulnerabilityByFlag(
            roomsRepository.fetchChallengeId(roomId), sendKeyRequest.getFlag());

    // 脆弱性が存在しない場合
    if (vulnerability == null) {
      return new SendKeyResponse(null, false, null);
    }

    // ゲームが存在する場合
    if (gamesRepository.fetchGame(userId, roomId, vulnerability.getVulnerabilityId(), (byte) 2)
        != null) {
      return new SendKeyResponse(false, true, null);
    }

    return new SendKeyResponse(
        true, true, scoresRepository.fetchScore((byte) 2, vulnerability.isDifficult()));
  }
}
