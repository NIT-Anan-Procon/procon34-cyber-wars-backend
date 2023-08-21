package com.example.procon34_CYBER_WARS_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.procon34_CYBER_WARS_backend.dto.Users.Credentials.LoginUserRequest;
import com.example.procon34_CYBER_WARS_backend.dto.Users.Credentials.LoginUserResponse;
import com.example.procon34_CYBER_WARS_backend.service.UsersCredentialsService;
import com.example.procon34_CYBER_WARS_backend.util.LoginChecker;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users/credentials")
public class UsersCredentialsController {

    private final UsersCredentialsService usersCredentialsService;
    private final LoginChecker loginChecker;

    @Autowired
    public UsersCredentialsController(UsersCredentialsService usersCredentialsService, LoginChecker loginChecker) {
        this.usersCredentialsService = usersCredentialsService;
        this.loginChecker = loginChecker;
    }

    // ユーザーログイン
    @PostMapping
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginUserRequest loginUserRequest,
            HttpServletRequest httpServletRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        LoginUserResponse loginUserResponse = usersCredentialsService.loginUser(loginUserRequest, httpServletRequest);
        return ResponseEntity.ok(loginUserResponse);
    }

    // ユーザーログアウト
    @DeleteMapping
    public ResponseEntity<?> logoutUser(HttpServletRequest httpServletRequest) {
        if (!loginChecker.checkLogin(httpServletRequest)) {
            return ResponseEntity.status(401).body("認証が必要です");
        }
        usersCredentialsService.logoutUser(httpServletRequest);
        return ResponseEntity.ok().build();
    }

}
