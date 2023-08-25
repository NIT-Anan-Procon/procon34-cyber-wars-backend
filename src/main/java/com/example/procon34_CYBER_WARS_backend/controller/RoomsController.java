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

import com.example.procon34_CYBER_WARS_backend.dto.rooms.CreateRequest;
import com.example.procon34_CYBER_WARS_backend.dto.rooms.JoinRequest;
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
    public ResponseEntity<?> create(@Validated @RequestBody final CreateRequest createRequest,
            final BindingResult bindingResult, final HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        return ResponseEntity.ok(roomsService.create(createRequest, httpServletRequest));
    }

    // ルーム参加
    @PutMapping
    @ResponseBody
    public ResponseEntity<?> join(@Validated @RequestBody final JoinRequest joinRequest,
            final BindingResult bindingResult, final HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        return ResponseEntity.ok(roomsService.join(joinRequest, httpServletRequest));
    }

    // ルーム情報取得
    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getInformation(final HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(roomsService.getInformation(httpServletRequest));
    }

    // ルーム退出
    @DeleteMapping
    public ResponseEntity<?> leave(final HttpServletRequest httpServletRequest) {
        roomsService.leave(httpServletRequest);
        return ResponseEntity.ok().build();
    }

}
