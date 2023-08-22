package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.procon34_CYBER_WARS_backend.dto.rooms.CreateRoomRequest;
import com.example.procon34_CYBER_WARS_backend.dto.rooms.CreateRoomResponse;
import com.example.procon34_CYBER_WARS_backend.repository.RoomsMapper;
import com.example.procon34_CYBER_WARS_backend.utilities.Random4DigitNumberGenerator;
import com.example.procon34_CYBER_WARS_backend.utilities.UserIdGetter;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class RoomsService {

    private final RoomsMapper roomsMapper;
    private final UserIdGetter userIdGetter;
    private final Random4DigitNumberGenerator random4DigitNumberGenerator;

    @Autowired
    public RoomsService(RoomsMapper roomsMapper, UserIdGetter userIdGetter,
            Random4DigitNumberGenerator random4DigitNumberGenerator) {
        this.roomsMapper = roomsMapper;
        this.userIdGetter = userIdGetter;
        this.random4DigitNumberGenerator = random4DigitNumberGenerator;
    }

    private final CreateRoomResponse createRoomResponse = new CreateRoomResponse();

    // ルーム作成
    public CreateRoomResponse createRoom(CreateRoomRequest createRoomRequest, HttpServletRequest httpServletRequest) {
        int userId = userIdGetter.getUserId(httpServletRequest);
        createRoomRequest.setUser_id(userId);
        short inviteId = random4DigitNumberGenerator.generateRandom4DigitNumber();
        createRoomRequest.setInvite_id(inviteId);
        createRoomResponse.setInvite_id(inviteId);
        roomsMapper.createRoom(createRoomRequest);
        roomsMapper.allocateRoom(createRoomRequest);
        return createRoomResponse;
    }

}
