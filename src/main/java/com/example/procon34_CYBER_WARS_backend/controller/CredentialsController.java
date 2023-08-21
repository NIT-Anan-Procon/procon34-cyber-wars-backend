package com.example.procon34_CYBER_WARS_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.procon34_CYBER_WARS_backend.dto.Users.RegisterUserRequest;
import com.example.procon34_CYBER_WARS_backend.dto.Users.UpdateUserNameResponse;
import com.example.procon34_CYBER_WARS_backend.service.UsersCredentialsService;
import com.example.procon34_CYBER_WARS_backend.util.LoginChecker;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users/credentials")
public class CredentialsController {

    private final UsersCredentialsService usersCredentialsService;
    private final LoginChecker loginChecker;

    @Autowired
    public CredentialsController(UsersCredentialsService usersCredentialsService, LoginChecker loginChecker) {
        this.usersCredentialsService = usersCredentialsService;
        this.loginChecker = loginChecker;
    }

    private UpdateUserNameResponse usersResponse = new UpdateUserNameResponse();

    // ユーザーログイン
    @PostMapping
    public ResponseEntity<?> loginUser(@Valid @RequestBody RegisterUserRequest usersRequest,
            BindingResult bindingResult,
            HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        usersResponse = usersCredentialsService.loginUser(usersRequest, usersResponse, request);
        return ResponseEntity.ok(usersResponse);
    }

    // ユーザーログアウト
    @DeleteMapping
    public ResponseEntity<?> logoutUser(HttpServletRequest request) {
        if (!loginChecker.checkLogin(request)) {
            return ResponseEntity.status(401).body("認証が必要です");
        }
        usersCredentialsService.logoutUser(request);
        return ResponseEntity.ok().build();
    }

}
