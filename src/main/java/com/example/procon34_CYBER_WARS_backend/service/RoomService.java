package com.example.procon34_CYBER_WARS_backend.service;

import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.procon34_CYBER_WARS_backend.dto.room.CreateRequest;
import com.example.procon34_CYBER_WARS_backend.dto.room.CreateResponse;
import com.example.procon34_CYBER_WARS_backend.dto.room.GetInformationResponse;
import com.example.procon34_CYBER_WARS_backend.dto.room.JoinRequest;
import com.example.procon34_CYBER_WARS_backend.dto.room.JoinResponse;
import com.example.procon34_CYBER_WARS_backend.entity.Rooms;
import com.example.procon34_CYBER_WARS_backend.repository.RoomRepository;
import com.example.procon34_CYBER_WARS_backend.utility.UserManager;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomService {

    private final RoomRepository roomRepository;
    private final UserManager userManager;

    // ルーム作成
    public CreateResponse create(final CreateRequest createRequest,
            final HttpServletRequest httpServletRequest) {
        final short inviteId = generate4DigitRandomNumber();

        roomRepository.create(inviteId, createRequest.isDifficult());
        roomRepository.allocate(userManager.getUserId(httpServletRequest));

        return new CreateResponse(inviteId);
    }

    // ルーム参加
    public JoinResponse join(final JoinRequest joinRequest,
            final HttpServletRequest httpServletRequest) {
        final short inviteId = joinRequest.getInviteId();

        // ルームが存在しない場合
        if (roomRepository.getRoomByInviteId(inviteId) == null) {
            return new JoinResponse(false);
        }

        roomRepository.join(userManager.getUserId(httpServletRequest), joinRequest.getInviteId());

        return new JoinResponse(true);
    }

    // ルーム情報取得
    public GetInformationResponse getInformation(final HttpServletRequest httpServletRequest) {
        final int userId = userManager.getUserId(httpServletRequest);
        final String opponentName = roomRepository.getOpponentName(userId);

        // 対戦相手が存在しない場合
        if (opponentName == null) {
            System.out.println("null");
            return new GetInformationResponse(roomRepository.isHost(userId), null);
        }

        return new GetInformationResponse(roomRepository.isHost(userId), opponentName);
    }

    // ルーム退出
    public void leave(final HttpServletRequest httpServletRequest) {
        roomRepository.leave(userManager.getUserId(httpServletRequest));
    }

    // 4桁乱数生成
    public short generate4DigitRandomNumber() {
        short inviteId;

        while (true) {
            inviteId = (short) (new Random().nextInt(9000) + 1000);
            for (final Rooms room : roomRepository.getNotStartedRooms()) {
                // 招待IDが等しい場合
                if (inviteId == room.getInviteId()) {
                    continue;
                }
            }
            break;
        }

        return inviteId;
    }

}
