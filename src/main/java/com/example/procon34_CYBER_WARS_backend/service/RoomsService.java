package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.procon34_CYBER_WARS_backend.dto.rooms.CreateRoomRequest;
import com.example.procon34_CYBER_WARS_backend.dto.rooms.CreateRoomResponse;
import com.example.procon34_CYBER_WARS_backend.dto.rooms.GetRoomInformationResponse;
import com.example.procon34_CYBER_WARS_backend.dto.rooms.JoinRoomRequest;
import com.example.procon34_CYBER_WARS_backend.dto.rooms.JoinRoomResponse;
import com.example.procon34_CYBER_WARS_backend.repository.RoomsRepository;
import com.example.procon34_CYBER_WARS_backend.utility.FourDigitRandomNumberGenerator;
import com.example.procon34_CYBER_WARS_backend.utility.RoomGetterByInviteId;
import com.example.procon34_CYBER_WARS_backend.utility.UserIdGetter;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomsService {

    private final RoomsRepository roomsRepository;
    private final UserIdGetter userIdGetter;
    private final RoomGetterByInviteId roomGetterByInviteId;
    private final FourDigitRandomNumberGenerator fourDigitRandomNumberGenerator;

    // ルーム作成
    public CreateRoomResponse createRoom(final CreateRoomRequest createRoomRequest,
            final HttpServletRequest httpServletRequest) {
        final short inviteId = fourDigitRandomNumberGenerator.generateFourDigitRandomNumber();

        roomsRepository.createRoom(inviteId, createRoomRequest.isDifficult());
        roomsRepository.allocateRoom(userIdGetter.getUserId(httpServletRequest));

        return new CreateRoomResponse(inviteId);
    }

    // ルーム参加
    public JoinRoomResponse joinRoom(final JoinRoomRequest joinRoomRequest,
            final HttpServletRequest httpServletRequest) {
        final short inviteId = joinRoomRequest.getInviteId();

        // ルームが存在しない場合
        if (roomGetterByInviteId.getRoomByInviteId(inviteId) == null) {
            return new JoinRoomResponse(false);
        }

        roomsRepository.joinRoom(userIdGetter.getUserId(httpServletRequest), joinRoomRequest.getInviteId());

        return new JoinRoomResponse(true);
    }

    // ルーム情報取得
    public GetRoomInformationResponse getRoomInformation(final HttpServletRequest httpServletRequest) {
        return roomsRepository.getRoomInformation(userIdGetter.getUserId(httpServletRequest));
    }

    // ルーム退出
    public void leaveRoom(final HttpServletRequest httpServletRequest) {
        roomsRepository.leaveRoom(userIdGetter.getUserId(httpServletRequest));
    }

}
