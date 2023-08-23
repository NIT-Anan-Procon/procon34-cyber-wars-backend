package com.example.procon34_CYBER_WARS_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.procon34_CYBER_WARS_backend.dto.users.Credentials.LoginUserRequest;
import com.example.procon34_CYBER_WARS_backend.service.UsersCredentialsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/credentials")
public class UsersCredentialsController {

    private final UsersCredentialsService usersCredentialsService;

    // ユーザーログイン
    @PostMapping
    public ResponseEntity<?> loginUser(@Valid @RequestBody final LoginUserRequest loginUserRequest,
            final BindingResult bindingResult, final HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        return ResponseEntity.ok(usersCredentialsService.loginUser(loginUserRequest, httpServletRequest));
    }

    // ユーザーログインチェック
    @GetMapping
    public ResponseEntity<?> checkUserLogin(final HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(usersCredentialsService.checkUserLogin(httpServletRequest));
    }

    // ユーザーログアウト
    @DeleteMapping
    public ResponseEntity<?> logoutUser(final HttpServletRequest httpServletRequest) {
        usersCredentialsService.logoutUser(httpServletRequest);
        return ResponseEntity.ok().build();
    }

}
