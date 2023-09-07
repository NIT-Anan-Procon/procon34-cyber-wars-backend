package com.example.procon34_CYBER_WARS_backend.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.procon34_CYBER_WARS_backend.dto.game.battle.FetchRevisionResponse;
import com.example.procon34_CYBER_WARS_backend.dto.game.battle.SendKeyRequest;
import com.example.procon34_CYBER_WARS_backend.dto.game.battle.SendKeyResponse;
import com.example.procon34_CYBER_WARS_backend.entity.Challenges;
import com.example.procon34_CYBER_WARS_backend.repository.AllocationsRepository;
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
        final String path = "defense" + roomId + "-" + allocationsRepository.fetchOpponentUserId(userId, roomId)
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
