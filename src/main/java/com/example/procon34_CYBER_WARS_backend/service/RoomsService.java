package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.procon34_CYBER_WARS_backend.dto.rooms.CreateRoomRequest;
import com.example.procon34_CYBER_WARS_backend.dto.rooms.CreateRoomResponse;
import com.example.procon34_CYBER_WARS_backend.entity.Allocations;
import com.example.procon34_CYBER_WARS_backend.entity.Rooms;
import com.example.procon34_CYBER_WARS_backend.entity.Vulnerabilities;
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

    // ルーム作成
    public CreateRoomResponse createRoom(final CreateRoomRequest createRoomRequest,
            final HttpServletRequest httpServletRequest) {
        final short inviteId = fourDigitRandomNumberGenerator.generateFourDigitRandomNumber();

        final Rooms room = Rooms.builder()
                .invite_id(inviteId)
                .build();
        final Vulnerabilities vulnerability = Vulnerabilities.builder()
                .difficult(createRoomRequest.isDifficult())
                .build();
        roomsRepository.createRoom(room, vulnerability);

        final Allocations allocation = Allocations.builder()
                .user_id(userIdGetter.getUserId(httpServletRequest))
                .build();
        roomsRepository.allocateRoom(allocation);

        final CreateRoomResponse createRoomResponse = CreateRoomResponse.builder()
                .invite_id(inviteId)
                .build();
        return createRoomResponse;
    }

}
