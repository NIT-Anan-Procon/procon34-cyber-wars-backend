package com.example.procon34_CYBER_WARS_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.procon34_CYBER_WARS_backend.dto.user.credential.LogInRequest;
import com.example.procon34_CYBER_WARS_backend.dto.utility.HttpClientErrorHandlerResponse;
import com.example.procon34_CYBER_WARS_backend.service.UserCredentialService;
import com.example.procon34_CYBER_WARS_backend.utility.HttpClientErrorHandler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user/credential")
@RequiredArgsConstructor
public class UserCredentialController {

    private final HttpClientErrorHandler httpClientErrorHandler;
    private final UserCredentialService userCredentialService;

    // ユーザーログイン
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> logIn(@Validated @RequestBody final LogInRequest logInRequest,
            final BindingResult bindingResult, final HttpServletRequest httpServletRequest) {
        final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse = httpClientErrorHandler
                .handle(bindingResult, null);
        if (httpClientErrorHandlerResponse.isError()) {
            return httpClientErrorHandlerResponse.getResponseEntity();
        }
        return ResponseEntity.ok(userCredentialService.logIn(logInRequest, httpServletRequest));
    }

    // ユーザーログインチェック
    @GetMapping
    @ResponseBody
    public ResponseEntity<?> isLoggedIn(final HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(userCredentialService.isLoggedIn(httpServletRequest));
    }

    // ユーザーログアウト
    @DeleteMapping
    public ResponseEntity<?> logOut(final HttpServletRequest httpServletRequest) {
        final HttpClientErrorHandlerResponse httpClientErrorHandlerResponse = httpClientErrorHandler
                .handle(null, httpServletRequest);
        if (httpClientErrorHandlerResponse.isError()) {
            return httpClientErrorHandlerResponse.getResponseEntity();
        }
        userCredentialService.logOut(httpServletRequest);
        return ResponseEntity.ok().build();
    }

}