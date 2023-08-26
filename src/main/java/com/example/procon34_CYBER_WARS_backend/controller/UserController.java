package com.example.procon34_CYBER_WARS_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.procon34_CYBER_WARS_backend.dto.user.RegisterRequest;
import com.example.procon34_CYBER_WARS_backend.dto.user.UpdateNameRequest;
import com.example.procon34_CYBER_WARS_backend.dto.user.UpdatePasswordRequest;
import com.example.procon34_CYBER_WARS_backend.dto.utility.HttpClientErrorHandlerResponse;
import com.example.procon34_CYBER_WARS_backend.service.UserService;
import com.example.procon34_CYBER_WARS_backend.utility.HttpClientErrorHandler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final HttpClientErrorHandler httpClientErrorHandler;
    private final UserService userService;

    // ユーザー登録
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> register(@Validated @RequestBody final RegisterRequest registerRequest,
            final BindingResult bindingResult) {
        final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse = httpClientErrorHandler
                .handle(bindingResult, null);
        if (httpClientErrorHandlerResponse.isError()) {
            return httpClientErrorHandlerResponse.getResponseEntity();
        }
        return ResponseEntity.ok(userService.register(registerRequest));
    }

    // ユーザー名更新
    @PatchMapping("/name")
    @ResponseBody
    public ResponseEntity<?> updateName(@Validated @RequestBody final UpdateNameRequest updateNameRequest,
            final BindingResult bindingResult, final HttpServletRequest httpServletRequest) {
        final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse = httpClientErrorHandler
                .handle(bindingResult, httpServletRequest);
        if (httpClientErrorHandlerResponse.isError()) {
            return httpClientErrorHandlerResponse.getResponseEntity();
        }
        return ResponseEntity.ok(userService.updateName(updateNameRequest, httpServletRequest));
    }

    // ユーザーパスワード更新
    @PatchMapping("/password")
    @ResponseBody
    public ResponseEntity<?> updatePassword(@Validated @RequestBody final UpdatePasswordRequest updatePasswordRequest,
            final BindingResult bindingResult, final HttpServletRequest httpServletRequest) {
        final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse = httpClientErrorHandler
                .handle(bindingResult, httpServletRequest);
        if (httpClientErrorHandlerResponse.isError()) {
            return httpClientErrorHandlerResponse.getResponseEntity();
        }
        return ResponseEntity.ok(userService.updatePassword(updatePasswordRequest, httpServletRequest));
    }

}