package jp.ac.anan.procon.CYBER_WARS.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.javaparser.ParseProblemException;
import com.github.javaparser.StaticJavaParser;

import jakarta.servlet.http.HttpServletRequest;
import jp.ac.anan.procon.CYBER_WARS.dto.game.defense.FetchCodeResponse;
import jp.ac.anan.procon.CYBER_WARS.dto.game.defense.SendCodeRequest;
import jp.ac.anan.procon.CYBER_WARS.dto.game.defense.SendCodeResponse;
import jp.ac.anan.procon.CYBER_WARS.repository.RoomsRepository;
import jp.ac.anan.procon.CYBER_WARS.utility.rooms.RoomIdFetcher;
import jp.ac.anan.procon.CYBER_WARS.utility.users.UserIdFetcher;
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
