package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.procon34_CYBER_WARS_backend.dto.Rooms.CreateRoomRequest;
import com.example.procon34_CYBER_WARS_backend.dto.Rooms.CreateRoomResponse;
import com.example.procon34_CYBER_WARS_backend.repository.RoomsMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class RoomsService {

    private final RoomsMapper roomsMapper;

    @Autowired
    public RoomsService(RoomsMapper roomsMapper) {
        this.roomsMapper = roomsMapper;
    }

    private final CreateRoomResponse createRoomResponse = new CreateRoomResponse();

    // ルーム作成
    public CreateRoomResponse createRoom(CreateRoomRequest createRoomRequest, HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession(false);
        createRoomRequest.setUserId((int) httpSession.getAttribute("sessionId"));
        short inviteId = 1234;
        createRoomRequest.setInviteId(inviteId);
        createRoomResponse.setInvite_id(inviteId);
        roomsMapper.createRoom(createRoomRequest);
        roomsMapper.allocateRoom(createRoomRequest);
        return createRoomResponse;
    }

}
