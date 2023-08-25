package com.example.procon34_CYBER_WARS_backend.service;

import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.procon34_CYBER_WARS_backend.dto.rooms.CreateRequest;
import com.example.procon34_CYBER_WARS_backend.dto.rooms.CreateResponse;
import com.example.procon34_CYBER_WARS_backend.dto.rooms.GetInformationResponse;
import com.example.procon34_CYBER_WARS_backend.dto.rooms.JoinRequest;
import com.example.procon34_CYBER_WARS_backend.dto.rooms.JoinResponse;
import com.example.procon34_CYBER_WARS_backend.entity.Rooms;
import com.example.procon34_CYBER_WARS_backend.repository.RoomsRepository;
import com.example.procon34_CYBER_WARS_backend.utility.UserManager;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomsService {

    private final RoomsRepository roomsRepository;
    private final UserManager userManager;

    // ルーム作成
    public CreateResponse create(final CreateRequest createRequest,
            final HttpServletRequest httpServletRequest) {
        final short inviteId = generate4DigitRandomNumber();

        roomsRepository.create(inviteId, createRequest.isDifficult());
        roomsRepository.allocate(userManager.getUserId(httpServletRequest));

        return new CreateResponse(inviteId);
    }

    // ルーム参加
    public JoinResponse join(final JoinRequest joinRequest,
            final HttpServletRequest httpServletRequest) {
        final short inviteId = joinRequest.getInviteId();

        // ルームが存在しない場合
        if (getRoomByInviteId(inviteId) == null) {
            return new JoinResponse(false);
        }

        roomsRepository.join(userManager.getUserId(httpServletRequest), joinRequest.getInviteId());

        return new JoinResponse(true);
    }

    // ルーム情報取得
    public GetInformationResponse getInformation(final HttpServletRequest httpServletRequest) {
        return roomsRepository.getInformation(userManager.getUserId(httpServletRequest));
    }

    // ルーム退出
    public void leave(final HttpServletRequest httpServletRequest) {
        roomsRepository.leave(userManager.getUserId(httpServletRequest));
    }

    // ルーム取得 by 招待ID
    public Rooms getRoomByInviteId(final short inviteId) {
        return roomsRepository.getRoomByInviteId(inviteId);
    }

    // 4桁乱数生成
    public short generate4DigitRandomNumber() {
        short inviteId;
        while (true) {
            inviteId = (short) (new Random().nextInt(9000) + 1000);
            for (final Rooms room : roomsRepository.getNotStartedRooms()) {
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
