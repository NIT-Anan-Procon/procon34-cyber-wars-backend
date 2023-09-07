package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.procon34_CYBER_WARS_backend.dto.room.CreateRequest;
import com.example.procon34_CYBER_WARS_backend.dto.room.CreateResponse;
import com.example.procon34_CYBER_WARS_backend.dto.room.FetchInformationResponse;
import com.example.procon34_CYBER_WARS_backend.dto.room.JoinRequest;
import com.example.procon34_CYBER_WARS_backend.dto.room.JoinResponse;
import com.example.procon34_CYBER_WARS_backend.repository.AllocationsRepository;
import com.example.procon34_CYBER_WARS_backend.repository.RoomsRepository;
import com.example.procon34_CYBER_WARS_backend.utility.allocations.OpponentNameFetcher;
import com.example.procon34_CYBER_WARS_backend.utility.rooms.RandomNumberGenerator;
import com.example.procon34_CYBER_WARS_backend.utility.rooms.RoomCloser;
import com.example.procon34_CYBER_WARS_backend.utility.rooms.RoomIdFetcher;
import com.example.procon34_CYBER_WARS_backend.utility.users.UserIdFetcher;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {

    private final RoomsRepository roomsRepository;
    private final AllocationsRepository allocationsRepository;
    private final UserIdFetcher userIdFetcher;
    private final RoomCloser roomCloser;
    private final RoomIdFetcher roomIdFetcher;
    private final RandomNumberGenerator randomNumberGenerator;
    private final OpponentNameFetcher opponentNameFetcher;

    // ルーム作成
    public CreateResponse create(final CreateRequest createRequest, final HttpServletRequest httpServletRequest) {
        final short inviteId = randomNumberGenerator.generateRandomNumber();

        roomsRepository.create(inviteId, createRequest.isDifficult());
        allocationsRepository.join(userIdFetcher.fetchUserId(httpServletRequest), inviteId, true);

        return new CreateResponse(inviteId);
    }

    // ルーム参加
    public JoinResponse join(final JoinRequest joinRequest, final HttpServletRequest httpServletRequest) {
        final short inviteId = joinRequest.getInviteId();

        // ルームが存在しない場合
        if (roomsRepository.fetchRoomByInviteId(inviteId) == null) {
            return new JoinResponse(false);
        }

        allocationsRepository.join(userIdFetcher.fetchUserId(httpServletRequest), joinRequest.getInviteId(), false);

        return new JoinResponse(true);
    }

    // ルーム情報取得
    public FetchInformationResponse fetchInformation(final HttpServletRequest httpServletRequest) {
        final int userId = userIdFetcher.fetchUserId(httpServletRequest);
        final int roomId = roomIdFetcher.fetchRoomId(userId);
        final String opponentName = opponentNameFetcher.fetchOpponentName(userId, roomId);

        // ルームが動作をしていない場合 and 相手ユーザー名が存在する場合
        if (!roomsRepository.isActive(roomId) && opponentName != null) {
            return new FetchInformationResponse(opponentName, allocationsRepository.isHost(userId), true);
        }

        return new FetchInformationResponse(opponentName, allocationsRepository.isHost(userId), false);
    }

    // ルーム退出
    public void exit(final HttpServletRequest httpServletRequest) {
        final int userId = userIdFetcher.fetchUserId(httpServletRequest);

        // ユーザーがホストである場合
        if (allocationsRepository.isHost(userId)) {
            roomCloser.close(roomIdFetcher.fetchRoomId(userId));
        }

        allocationsRepository.exit(userId);
    }

}
