package com.example.procon34_CYBER_WARS_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.procon34_CYBER_WARS_backend.dto.UsersRequest;
import com.example.procon34_CYBER_WARS_backend.dto.UsersResponse;
import com.example.procon34_CYBER_WARS_backend.service.UsersService;
import com.example.procon34_CYBER_WARS_backend.util.LoginChecker;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    private UsersService usersService;
    private LoginChecker loginChecker;

    @Autowired
    public UsersController(UsersService usersService, LoginChecker loginChecker) {
        this.usersService = usersService;
        this.loginChecker = loginChecker;
    }

    private UsersResponse usersResponse = new UsersResponse();

    // ユーザー登録
    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody UsersRequest usersRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        } else {
            usersResponse = usersService.register(usersRequest, usersResponse);
            return ResponseEntity.ok(usersResponse);
        }
    }

    // ユーザー情報変更
    @PatchMapping
    public ResponseEntity<?> update(@Valid @RequestBody UsersRequest usersRequest, BindingResult bindingResult,
            HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        } else {
            if (loginChecker.checkLogin(request)) {
                usersResponse = usersService.update(usersRequest, usersResponse, request);
                return ResponseEntity.ok(usersResponse);
            } else {
                return ResponseEntity.status(401).body("認証が必要です");
            }
        }
    }

    // ユーザーログイン
    @PostMapping("/credentials")
    public ResponseEntity<?> login(@Valid @RequestBody UsersRequest usersRequest, BindingResult bindingResult,
            HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        } else {
            usersResponse = usersService.login(usersRequest, usersResponse, request);
            return ResponseEntity.ok(usersResponse);
        }
    }

    // ユーザーログアウト

}
