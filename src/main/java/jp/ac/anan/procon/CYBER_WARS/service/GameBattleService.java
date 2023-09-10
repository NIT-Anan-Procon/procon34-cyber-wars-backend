package jp.ac.anan.procon.CYBER_WARS.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpServletRequest;
import jp.ac.anan.procon.CYBER_WARS.dto.game.battle.FetchRevisionResponse;
import jp.ac.anan.procon.CYBER_WARS.dto.game.battle.SendKeyRequest;
import jp.ac.anan.procon.CYBER_WARS.dto.game.battle.SendKeyResponse;
import jp.ac.anan.procon.CYBER_WARS.entity.Challenges;
import jp.ac.anan.procon.CYBER_WARS.repository.AllocationsRepository;
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
        final String path = "revision" + roomId + "-" + allocationsRepository.fetchOpponentUserId(userId, roomId)
                + ".php";

        try {
            return new FetchRevisionResponse(path, Files.readString(Paths.get(path)));
        } catch (final IOException exception) {
            return new FetchRevisionResponse(null, null);
        }
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
        if (gamesRepository.fetchGame(userId, roomId, vulnerability.getVulnerabilityId(), (byte) 2) != null) {
            return new SendKeyResponse(false, true, null);
        }

        return new SendKeyResponse(true, true, scoresRepository.fetchScore((byte) 2, vulnerability.isDifficult()));
    }

}
