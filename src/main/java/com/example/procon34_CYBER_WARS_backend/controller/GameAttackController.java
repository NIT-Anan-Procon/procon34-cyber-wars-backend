package com.example.procon34_CYBER_WARS_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.procon34_CYBER_WARS_backend.dto.room.JoinRequest;
import com.example.procon34_CYBER_WARS_backend.dto.utility.HttpClientErrorHandlerResponse;
import com.example.procon34_CYBER_WARS_backend.service.GameService;
import com.example.procon34_CYBER_WARS_backend.utility.HttpClientErrorHandler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/game/attack")
@RequiredArgsConstructor
public class GameAttackController {

    private final HttpClientErrorHandler httpClientErrorHandler;
    private final GameService gameService;

    // アタックフェーズ：課題取得
    @GetMapping("/challenge")
    @ResponseBody
    public ResponseEntity<?> fetchChallenge(final HttpServletRequest httpServletRequest) {
        final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse = httpClientErrorHandler
                .handle(null, httpServletRequest);
        if (httpClientErrorHandlerResponse.isError()) {
            return httpClientErrorHandlerResponse.getResponseEntity();
        }
        return ResponseEntity.ok(gameService);
    }

    // アタックフェーズ：ヒント使用
    @PostMapping("/hint")
    @ResponseBody
    public ResponseEntity<?> useHint(@Validated @RequestBody final JoinRequest joinRequest,
            final BindingResult bindingResult, final HttpServletRequest httpServletRequest) {
        final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse = httpClientErrorHandler
                .handle(bindingResult, httpServletRequest);
        if (httpClientErrorHandlerResponse.isError()) {
            return httpClientErrorHandlerResponse.getResponseEntity();
        }
        return ResponseEntity.ok(gameService);
    }

    // アタックフェーズ：キー送信
    @PostMapping("/key")
    @ResponseBody
    public ResponseEntity<?> sendKey(@Validated @RequestBody final JoinRequest joinRequest,
            final BindingResult bindingResult, final HttpServletRequest httpServletRequest) {
        final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse = httpClientErrorHandler
                .handle(bindingResult, httpServletRequest);
        if (httpClientErrorHandlerResponse.isError()) {
            return httpClientErrorHandlerResponse.getResponseEntity();
        }
        return ResponseEntity.ok(gameService);
    }

}
