package jp.ac.anan.procon.cyber_wars.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.servlet.http.HttpServletRequest;
import jp.ac.anan.procon.cyber_wars.application.utility.rooms.RoomIdFetcher;
import jp.ac.anan.procon.cyber_wars.application.utility.users.UserIdFetcher;
import jp.ac.anan.procon.cyber_wars.domain.dto.game.attack.FetchChallengeResponse;
import jp.ac.anan.procon.cyber_wars.domain.dto.game.attack.SendKeyRequest;
import jp.ac.anan.procon.cyber_wars.domain.dto.game.attack.SendKeyResponse;
import jp.ac.anan.procon.cyber_wars.domain.dto.game.attack.UseHintRequest;
import jp.ac.anan.procon.cyber_wars.domain.entity.Challenges;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.ChallengesRepository;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.GamesRepository;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.RoomsRepository;
import jp.ac.anan.procon.cyber_wars.infrastructure.repository.ScoresRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class GameAttackService {

  private final RoomsRepository roomsRepository;
  private final GamesRepository gamesRepository;
  private final ChallengesRepository challengesRepository;
  private final ScoresRepository scoresRepository;
  private final UserIdFetcher userIdFetcher;
  private final RoomIdFetcher roomIdFetcher;

  // 課題取得
  public FetchChallengeResponse fetchChallenge(final HttpServletRequest httpServletRequest) {
    final int challengeId =
        roomsRepository.fetchChallengeId(
            roomIdFetcher.fetchRoomId(userIdFetcher.fetchUserId(httpServletRequest)));

    return new FetchChallengeResponse(
        "target" + challengeId + ".php", challengesRepository.fetchVulnerabilities(challengeId));
  }

  // ヒント使用
  public void useHint(
      final UseHintRequest useHintRequest, final HttpServletRequest httpServletRequest) {
    final int userId = userIdFetcher.fetchUserId(httpServletRequest);

    gamesRepository.addScore(
        userId, roomIdFetcher.fetchRoomId(userId), useHintRequest.getVulnerabilityId(), (byte) 1);
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
    if (gamesRepository.fetchGame(userId, roomId, vulnerability.getVulnerabilityId(), (byte) 0)
        != null) {
      return new SendKeyResponse(false, true, null);
    }

    return new SendKeyResponse(
        true, true, scoresRepository.fetchScore((byte) 0, vulnerability.isDifficult()));
  }
}
