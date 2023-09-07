package com.example.procon34_CYBER_WARS_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.procon34_CYBER_WARS_backend.dto.game.defense.SendCodeRequest;
import com.example.procon34_CYBER_WARS_backend.dto.utility.HttpClientErrorHandlerResponse;
import com.example.procon34_CYBER_WARS_backend.service.GameService;
import com.example.procon34_CYBER_WARS_backend.utility.HttpClientErrorHandler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/game/defense")
@RequiredArgsConstructor
public class GameDefenseController {

    private final HttpClientErrorHandler httpClientErrorHandler;
    private final GameService gameService;

    // ディフェンスフェーズ：コード取得
    @GetMapping("/code")
    @ResponseBody
    public ResponseEntity<?> fetchCode(final HttpServletRequest httpServletRequest) {
        final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse = httpClientErrorHandler
                .handle(null, httpServletRequest);
        if (httpClientErrorHandlerResponse.isError()) {
            return httpClientErrorHandlerResponse.getResponseEntity();
        }
        return ResponseEntity.ok(gameService);
    }

    // ディフェンスフェーズ：コード送信
    @PutMapping("/code")
    @ResponseBody
    public ResponseEntity<?> sendCode(@Validated @RequestBody final SendCodeRequest sendCodeRequest,
            final BindingResult bindingResult, final HttpServletRequest httpServletRequest) {
        final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse = httpClientErrorHandler
                .handle(bindingResult, httpServletRequest);
        if (httpClientErrorHandlerResponse.isError()) {
            return httpClientErrorHandlerResponse.getResponseEntity();
        }
        return ResponseEntity.ok(gameService);
    }

}
