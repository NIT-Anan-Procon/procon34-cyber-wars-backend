package com.example.procon34_CYBER_WARS_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.procon34_CYBER_WARS_backend.dto.rooms.CreateRoomRequest;
import com.example.procon34_CYBER_WARS_backend.service.RoomsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomsController {

    private final RoomsService roomsService;

    // ルーム作成
    @PostMapping
    public ResponseEntity<?> updateName(@Valid @RequestBody final CreateRoomRequest createRoomRequest,
            final BindingResult bindingResult, final HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        return ResponseEntity.ok(roomsService.createRoom(createRoomRequest, httpServletRequest));
    }

}
