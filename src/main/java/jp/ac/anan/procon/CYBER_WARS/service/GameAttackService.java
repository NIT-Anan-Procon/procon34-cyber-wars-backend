package jp.ac.anan.procon.CYBER_WARS.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpServletRequest;
import jp.ac.anan.procon.CYBER_WARS.dto.game.attack.FetchChallengeResponse;
import jp.ac.anan.procon.CYBER_WARS.dto.game.attack.SendKeyRequest;
import jp.ac.anan.procon.CYBER_WARS.dto.game.attack.SendKeyResponse;
import jp.ac.anan.procon.CYBER_WARS.dto.game.attack.UseHintRequest;
import jp.ac.anan.procon.CYBER_WARS.entity.Challenges;
import jp.ac.anan.procon.CYBER_WARS.repository.ChallengesRepository;
import jp.ac.anan.procon.CYBER_WARS.repository.GamesRepository;
import jp.ac.anan.procon.CYBER_WARS.repository.RoomsRepository;
import jp.ac.anan.procon.CYBER_WARS.repository.ScoresRepository;
import jp.ac.anan.procon.CYBER_WARS.utility.rooms.RoomIdFetcher;
import jp.ac.anan.procon.CYBER_WARS.utility.users.UserIdFetcher;
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
        final int challengeId = roomsRepository
                .fetchChallengeId(roomIdFetcher.fetchRoomId(userIdFetcher.fetchUserId(httpServletRequest)));

        return new FetchChallengeResponse("target" + challengeId + ".php",
                challengesRepository.fetchVulnerabilities(challengeId));
    }

    // ヒント使用
    public void useHint(final UseHintRequest useHintRequest, final HttpServletRequest httpServletRequest) {
        final int userId = userIdFetcher.fetchUserId(httpServletRequest);

        gamesRepository.addScore(userId, roomIdFetcher.fetchRoomId(userId), useHintRequest.getVulnerabilityId(),
                (byte) 1);
    }

    // キー送信
    public SendKeyResponse sendKey(final SendKeyRequest sendKeyRequest, final HttpServletRequest httpServletRequest) {
        final int userId = userIdFetcher.fetchUserId(httpServletRequest);
        final int roomId = roomIdFetcher.fetchRoomId(userId);
        final Challenges vulnerability = challengesRepository
                .fetchVulnerabilityByFlag(roomsRepository.fetchChallengeId(roomId), sendKeyRequest.getFlag());

        // 脆弱性が存在しない場合
        if (vulnerability == null) {
            return new SendKeyResponse(null, false, null);
        }

        // ゲームが存在する場合
        if (gamesRepository.fetchGame(userId, roomId, vulnerability.getVulnerabilityId(), (byte) 0) != null) {
            return new SendKeyResponse(false, true, null);
        }

        return new SendKeyResponse(true, true, scoresRepository.fetchScore((byte) 0, vulnerability.isDifficult()));
    }

}
