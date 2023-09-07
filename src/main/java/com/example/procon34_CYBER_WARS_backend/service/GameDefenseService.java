package com.example.procon34_CYBER_WARS_backend.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.procon34_CYBER_WARS_backend.dto.game.defense.FetchCodeResponse;
import com.example.procon34_CYBER_WARS_backend.dto.game.defense.SendCodeRequest;
import com.example.procon34_CYBER_WARS_backend.dto.game.defense.SendCodeResponse;
import com.example.procon34_CYBER_WARS_backend.repository.RoomsRepository;
import com.example.procon34_CYBER_WARS_backend.utility.rooms.RoomIdFetcher;
import com.example.procon34_CYBER_WARS_backend.utility.users.UserIdFetcher;
import com.github.javaparser.ParseProblemException;
import com.github.javaparser.StaticJavaParser;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class GameDefenseService {

    private final RoomsRepository roomsRepository;
    private final UserIdFetcher userIdFetcher;
    private final RoomIdFetcher roomIdFetcher;

    // コード取得
    public FetchCodeResponse fetchCode(final HttpServletRequest httpServletRequest) {
        final String path = "target" + roomsRepository
                .fetchChallengeId(roomIdFetcher.fetchRoomId(userIdFetcher.fetchUserId((httpServletRequest)))) + ".php";

        try {
            return new FetchCodeResponse(path, Files.readString(Paths.get(path)));
        } catch (final IOException exception) {
            return new FetchCodeResponse(null, null);
        }
    }

    // コード送信
    public SendCodeResponse sendCode(final SendCodeRequest sendCodeRequest,
            final HttpServletRequest httpServletRequest) {
        final int userId = userIdFetcher.fetchUserId(httpServletRequest);

        try {
            StaticJavaParser.parse(sendCodeRequest.getCode());
            try {
                Files.writeString(Paths.get("revision" + roomIdFetcher.fetchRoomId(userId) + "-" + userId + ".php"),
                        sendCodeRequest.getCode());
                return new SendCodeResponse(true);
            } catch (final IOException exception) {
                return new SendCodeResponse(false);
            }
        } catch (final ParseProblemException exception) {
            return new SendCodeResponse(false);
        }
    }

}
