package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.stereotype.Service;

import com.example.procon34_CYBER_WARS_backend.dto.rooms.CreateRoomRequest;
import com.example.procon34_CYBER_WARS_backend.dto.rooms.CreateRoomResponse;
import com.example.procon34_CYBER_WARS_backend.mapper.RoomsMapper;
import com.example.procon34_CYBER_WARS_backend.utility.FourDigitRandomNumberGenerator;
import com.example.procon34_CYBER_WARS_backend.utility.UserIdGetter;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomsService {

    private final RoomsMapper roomsMapper;
    private final UserIdGetter userIdGetter;
    private final FourDigitRandomNumberGenerator fourDigitRandomNumberGenerator;

    private final CreateRoomResponse createRoomResponse = new CreateRoomResponse();

    // ルーム作成
    public CreateRoomResponse createRoom(final CreateRoomRequest createRoomRequest,
            final HttpServletRequest httpServletRequest) {
        final int userId = userIdGetter.getUserId(httpServletRequest);
        createRoomRequest.setUser_id(userId);

        final short inviteId = fourDigitRandomNumberGenerator.generateFourDigitRandomNumber();
        createRoomRequest.setInvite_id(inviteId);
        createRoomResponse.setInvite_id(inviteId);

        roomsMapper.createRoom(createRoomRequest);
        roomsMapper.allocateRoom(createRoomRequest);

        return createRoomResponse;
    }

}
