package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.procon34_CYBER_WARS_backend.dto.game.GetOpponentNameResponse;
import com.example.procon34_CYBER_WARS_backend.dto.game.GetScoresResponse;
import com.example.procon34_CYBER_WARS_backend.dto.game.GetStartTimeResponse;
import com.example.procon34_CYBER_WARS_backend.repository.GameRepository;
import com.example.procon34_CYBER_WARS_backend.utility.GameManager;
import com.example.procon34_CYBER_WARS_backend.utility.RoomManager;
import com.example.procon34_CYBER_WARS_backend.utility.UserManager;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class GameService {

    private final GameRepository gameRepository;
    private final UserManager userManager;
    private final RoomManager roomManager;
    private final GameManager gameManager;

    // ゲーム開始
    public void start(final HttpServletRequest httpServletRequest) {
        final int roomId = roomManager.getRoomId(userManager.getUserId(httpServletRequest));

        roomManager.close(roomId);
        gameRepository.setStartTime(roomId);
    }

    // ゲーム開始時刻取得
    public GetStartTimeResponse getStartTime(final HttpServletRequest httpServletRequest) {
        return new GetStartTimeResponse(
                gameRepository.getStartTime(roomManager.getRoomId(userManager.getUserId(httpServletRequest))));
    }

    // 相手ユーザー名取得
    public GetOpponentNameResponse getOpponentName(final HttpServletRequest httpServletRequest) {
        final int userId = userManager.getUserId(httpServletRequest);

        return new GetOpponentNameResponse(gameManager.getOpponentName(userId, roomManager.getRoomId(userId)));
    }

    // スコア取得
    public GetScoresResponse getScores(final HttpServletRequest httpServletRequest) {
        final int userId = userManager.getUserId(httpServletRequest);
        final int roomId = roomManager.getRoomId(userId);
        final short scores[] = { gameRepository.getMyScore(userId, roomId),
                gameRepository.getOpponentScore(userId, roomId) };
        return new GetScoresResponse(scores);
    }

}
