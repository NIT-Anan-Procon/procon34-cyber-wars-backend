package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.procon34_CYBER_WARS_backend.dto.Rooms.CreateRoomRequest;
import com.example.procon34_CYBER_WARS_backend.dto.Rooms.CreateRoomResponse;
import com.example.procon34_CYBER_WARS_backend.repository.RoomsMapper;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class RoomsService {

    private final RoomsMapper roomsMapper;

    @Autowired
    public RoomsService(RoomsMapper roomsMapper) {
        this.roomsMapper = roomsMapper;
    }

    private final CreateRoomResponse createRoomResponse = new CreateRoomResponse();

    public CreateRoomResponse createRoom(CreateRoomRequest createRoomRequest, HttpServletRequest httpServletRequest) {
        roomsMapper.createRoom(createRoomRequest);
        createRoomResponse.setInviteId(1234);
        return createRoomResponse;
    }

}
