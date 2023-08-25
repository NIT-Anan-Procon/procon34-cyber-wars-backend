package com.example.procon34_CYBER_WARS_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.procon34_CYBER_WARS_backend.dto.rooms.CreateRoomRequest;
import com.example.procon34_CYBER_WARS_backend.dto.rooms.JoinRoomRequest;
import com.example.procon34_CYBER_WARS_backend.service.RoomsService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomsController {

    private final RoomsService roomsService;

    // ルーム作成
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createRoom(@Validated @RequestBody final CreateRoomRequest createRoomRequest,
            final BindingResult bindingResult, final HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        return ResponseEntity.ok(roomsService.createRoom(createRoomRequest, httpServletRequest));
    }

    // ルーム参加
    @PutMapping
    @ResponseBody
    public ResponseEntity<?> joinRoom(@Validated @RequestBody final JoinRoomRequest joinRoomRequest,
            final BindingResult bindingResult, final HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        return ResponseEntity.ok(roomsService.joinRoom(joinRoomRequest, httpServletRequest));
    }

    // ルーム情報取得
    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getRoomInformation(final HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(roomsService.getRoomInformation(httpServletRequest));
    }

    // ルーム退出
    @DeleteMapping
    public ResponseEntity<?> leaveRoom(final HttpServletRequest httpServletRequest) {
        roomsService.leaveRoom(httpServletRequest);
        return ResponseEntity.ok().build();
    }

}
