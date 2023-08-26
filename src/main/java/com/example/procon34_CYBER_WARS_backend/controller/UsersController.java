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

import com.example.procon34_CYBER_WARS_backend.dto.HttpClientErrorHandlerResponse;
import com.example.procon34_CYBER_WARS_backend.dto.users.RegisterRequest;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdateNameRequest;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdatePasswordRequest;
import com.example.procon34_CYBER_WARS_backend.service.UsersService;
import com.example.procon34_CYBER_WARS_backend.utility.HttpClientErrorHandler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final HttpClientErrorHandler httpClientErrorHandler;
    private final UsersService usersService;

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
        return ResponseEntity.ok(usersService.register(registerRequest));
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
        return ResponseEntity.ok(usersService.updateName(updateNameRequest, httpServletRequest));
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
        return ResponseEntity.ok(usersService.updatePassword(updatePasswordRequest, httpServletRequest));
    }

}
