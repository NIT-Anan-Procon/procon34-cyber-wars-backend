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

import com.example.procon34_CYBER_WARS_backend.dto.room.CreateRequest;
import com.example.procon34_CYBER_WARS_backend.dto.room.JoinRequest;
import com.example.procon34_CYBER_WARS_backend.dto.utility.HttpClientErrorHandlerResponse;
import com.example.procon34_CYBER_WARS_backend.service.RoomService;
import com.example.procon34_CYBER_WARS_backend.utility.HttpClientErrorHandler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {

    private final HttpClientErrorHandler httpClientErrorHandler;
    private final RoomService roomService;

    // ルーム作成
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> create(@Validated @RequestBody final CreateRequest createRequest,
            final BindingResult bindingResult, final HttpServletRequest httpServletRequest) {
        final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse = httpClientErrorHandler
                .handle(bindingResult, httpServletRequest);
        if (httpClientErrorHandlerResponse.isError()) {
            return httpClientErrorHandlerResponse.getResponseEntity();
        }
        return ResponseEntity.ok(roomService.create(createRequest, httpServletRequest));
    }

    // ルーム参加
    @PutMapping
    @ResponseBody
    public ResponseEntity<?> join(@Validated @RequestBody final JoinRequest joinRequest,
            final BindingResult bindingResult, final HttpServletRequest httpServletRequest) {
        final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse = httpClientErrorHandler
                .handle(bindingResult, httpServletRequest);
        if (httpClientErrorHandlerResponse.isError()) {
            return httpClientErrorHandlerResponse.getResponseEntity();
        }
        return ResponseEntity.ok(roomService.join(joinRequest, httpServletRequest));
    }

    // ルーム情報取得
    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getInformation(final HttpServletRequest httpServletRequest) {
        final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse = httpClientErrorHandler
                .handle(null, httpServletRequest);
        if (httpClientErrorHandlerResponse.isError()) {
            return httpClientErrorHandlerResponse.getResponseEntity();
        }
        return ResponseEntity.ok(roomService.getInformation(httpServletRequest));
    }

    // ルーム退出
    @DeleteMapping
    public ResponseEntity<?> exit(final HttpServletRequest httpServletRequest) {
        final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse = httpClientErrorHandler
                .handle(null, httpServletRequest);
        if (httpClientErrorHandlerResponse.isError()) {
            return httpClientErrorHandlerResponse.getResponseEntity();
        }
        roomService.exit(httpServletRequest);
        return ResponseEntity.ok().build();
    }

}