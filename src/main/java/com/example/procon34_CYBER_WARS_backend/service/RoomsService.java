package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.procon34_CYBER_WARS_backend.dto.rooms.CreateRoomRequest;
import com.example.procon34_CYBER_WARS_backend.dto.rooms.CreateRoomResponse;
import com.example.procon34_CYBER_WARS_backend.repository.RoomsRepository;
import com.example.procon34_CYBER_WARS_backend.utility.FourDigitRandomNumberGenerator;
import com.example.procon34_CYBER_WARS_backend.utility.UserIdGetter;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomsService {

    private final RoomsRepository roomsRepository;
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

        roomsRepository.createRoom(createRoomRequest);
        roomsRepository.allocateRoom(createRoomRequest);

        return createRoomResponse;
    }

}
