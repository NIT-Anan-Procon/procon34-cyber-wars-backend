package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.procon34_CYBER_WARS_backend.dto.game.attack.FetchChallengeResponse;
import com.example.procon34_CYBER_WARS_backend.dto.game.attack.SendKeyRequest;
import com.example.procon34_CYBER_WARS_backend.dto.game.attack.SendKeyResponse;
import com.example.procon34_CYBER_WARS_backend.dto.game.attack.UseHintRequest;
import com.example.procon34_CYBER_WARS_backend.entity.Challenges;
import com.example.procon34_CYBER_WARS_backend.repository.ChallengesRepository;
import com.example.procon34_CYBER_WARS_backend.repository.GamesRepository;
import com.example.procon34_CYBER_WARS_backend.repository.RoomsRepository;
import com.example.procon34_CYBER_WARS_backend.repository.ScoresRepository;
import com.example.procon34_CYBER_WARS_backend.utility.rooms.RoomIdFetcher;
import com.example.procon34_CYBER_WARS_backend.utility.users.UserIdFetcher;

import jakarta.servlet.http.HttpServletRequest;
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
