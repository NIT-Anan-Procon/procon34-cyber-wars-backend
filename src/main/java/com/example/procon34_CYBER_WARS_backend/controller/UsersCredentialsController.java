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

import com.example.procon34_CYBER_WARS_backend.dto.users.Credentials.LogInRequest;
import com.example.procon34_CYBER_WARS_backend.service.UsersCredentialsService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users/credentials")
@RequiredArgsConstructor
public class UsersCredentialsController {

    private final UsersCredentialsService usersCredentialsService;

    // ユーザーログイン
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> logIn(@Validated @RequestBody final LogInRequest logInRequest,
            final BindingResult bindingResult, final HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        return ResponseEntity.ok(usersCredentialsService.logIn(logInRequest, httpServletRequest));
    }

    // ユーザーログインチェック
    @GetMapping
    @ResponseBody
    public ResponseEntity<?> isLoggedIn(final HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(usersCredentialsService.isLoggedIn(httpServletRequest));
    }

    // ユーザーログアウト
    @DeleteMapping
    public ResponseEntity<?> logOut(final HttpServletRequest httpServletRequest) {
        usersCredentialsService.logOut(httpServletRequest);
        return ResponseEntity.ok().build();
    }

}
