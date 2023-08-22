package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.procon34_CYBER_WARS_backend.dto.rooms.CreateRoomRequest;
import com.example.procon34_CYBER_WARS_backend.dto.rooms.CreateRoomResponse;
import com.example.procon34_CYBER_WARS_backend.repository.RoomsMapper;
import com.example.procon34_CYBER_WARS_backend.utilities.FourDigitRandomNumberGenerator;
import com.example.procon34_CYBER_WARS_backend.utilities.UserIdGetter;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class RoomsService {

    private final RoomsMapper roomsMapper;
    private final UserIdGetter userIdGetter;
    private final FourDigitRandomNumberGenerator fourDigitRandomNumberGenerator;

    @Autowired
    public RoomsService(RoomsMapper roomsMapper, UserIdGetter userIdGetter,
            FourDigitRandomNumberGenerator fourDigitRandomNumberGenerator) {
        this.roomsMapper = roomsMapper;
        this.userIdGetter = userIdGetter;
        this.fourDigitRandomNumberGenerator = fourDigitRandomNumberGenerator;
    }

    private final CreateRoomResponse createRoomResponse = new CreateRoomResponse();

    // ルーム作成
    public CreateRoomResponse createRoom(CreateRoomRequest createRoomRequest, HttpServletRequest httpServletRequest) {
        int userId = userIdGetter.getUserId(httpServletRequest);
        createRoomRequest.setUser_id(userId);
        short inviteId = fourDigitRandomNumberGenerator.generateFourDigitRandomNumber();
        createRoomRequest.setInvite_id(inviteId);
        createRoomResponse.setInvite_id(inviteId);
        roomsMapper.createRoom(createRoomRequest);
        roomsMapper.allocateRoom(createRoomRequest);
        return createRoomResponse;
    }

}
