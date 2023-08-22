package com.example.procon34_CYBER_WARS_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.procon34_CYBER_WARS_backend.dto.users.Credentials.CheckUserLoginResponse;
import com.example.procon34_CYBER_WARS_backend.dto.users.Credentials.LoginUserRequest;
import com.example.procon34_CYBER_WARS_backend.dto.users.Credentials.LoginUserResponse;
import com.example.procon34_CYBER_WARS_backend.service.UsersCredentialsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users/credentials")
public class UsersCredentialsController {

    private final UsersCredentialsService usersCredentialsService;

    @Autowired
    public UsersCredentialsController(UsersCredentialsService usersCredentialsService) {
        this.usersCredentialsService = usersCredentialsService;
    }

    // ユーザーログイン
    @PostMapping
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginUserRequest loginUserRequest,
            BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        LoginUserResponse loginUserResponse = usersCredentialsService.loginUser(loginUserRequest, httpServletRequest);
        return ResponseEntity.ok(loginUserResponse);
    }

    // ユーザーログインチェック
    @GetMapping
    public ResponseEntity<?> checkUserLogin(HttpServletRequest httpServletRequest) {
        CheckUserLoginResponse checkUserLoginResponse = usersCredentialsService.checkUserLogin(httpServletRequest);
        return ResponseEntity.ok(checkUserLoginResponse);
    }

    // ユーザーログアウト
    @DeleteMapping
    public ResponseEntity<?> logoutUser(HttpServletRequest httpServletRequest) {
        usersCredentialsService.logoutUser(httpServletRequest);
        return ResponseEntity.ok().build();
    }

}
