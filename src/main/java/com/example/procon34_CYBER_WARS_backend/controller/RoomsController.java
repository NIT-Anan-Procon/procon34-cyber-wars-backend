package com.example.procon34_CYBER_WARS_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.procon34_CYBER_WARS_backend.dto.Rooms.CreateRoomRequest;
import com.example.procon34_CYBER_WARS_backend.dto.Rooms.CreateRoomResponse;
import com.example.procon34_CYBER_WARS_backend.service.RoomsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/rooms")
public class RoomsController {

    private final RoomsService roomsService;

    @Autowired
    public RoomsController(RoomsService roomsService) {
        this.roomsService = roomsService;
    }

    // ルーム作成
    @PostMapping
    public ResponseEntity<?> updateUserName(@Valid @RequestBody CreateRoomRequest createRoomRequest,
            BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        CreateRoomResponse createRoomResponse = roomsService.createRoom(createRoomRequest, httpServletRequest);
        return ResponseEntity.ok(createRoomResponse);
    }

}
