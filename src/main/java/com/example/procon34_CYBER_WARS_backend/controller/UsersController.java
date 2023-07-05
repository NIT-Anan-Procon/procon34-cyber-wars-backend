package com.example.procon34_CYBER_WARS_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.procon34_CYBER_WARS_backend.dto.UsersRequest;
import com.example.procon34_CYBER_WARS_backend.dto.UsersResponse;
import com.example.procon34_CYBER_WARS_backend.dto.UsersUpdateRequest;
import com.example.procon34_CYBER_WARS_backend.service.UsersService;
import com.example.procon34_CYBER_WARS_backend.util.LoginChecker;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;
    private final LoginChecker loginChecker;

    @Autowired
    public UsersController(UsersService usersService, LoginChecker loginChecker) {
        this.usersService = usersService;
        this.loginChecker = loginChecker;
    }

    private UsersResponse usersResponse = new UsersResponse();

    // ユーザー登録
    @PostMapping
    public ResponseEntity<?> registerUser(@Valid @RequestBody UsersRequest usersRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        usersResponse = usersService.registerUser(usersRequest, usersResponse);
        return ResponseEntity.ok(usersResponse);
    }

    // ユーザー名変更
    @PatchMapping("/name")
    public ResponseEntity<?> updateUserName(@Valid @RequestBody UsersUpdateRequest usersUpdateRequest,
            BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        if (!loginChecker.checkLogin(request)) {
            return ResponseEntity.status(401).body("認証が必要です");
        }
        usersResponse = usersService.updateUserName(usersUpdateRequest, usersResponse, request);
        return ResponseEntity.ok(usersResponse);
    }

    // ユーザーパスワード変更
    @PatchMapping("/password")
    public ResponseEntity<?> updateUserPassword(@Valid @RequestBody UsersUpdateRequest usersUpdateRequest,
            BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        if (!loginChecker.checkLogin(request)) {
            return ResponseEntity.status(401).body("認証が必要です");
        }
        usersResponse = usersService.updateUserPassword(usersUpdateRequest, usersResponse, request);
        return ResponseEntity.ok(usersResponse);
    }

    // ユーザーログイン
    @PostMapping("/credentials")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UsersRequest usersRequest, BindingResult bindingResult,
            HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        usersResponse = usersService.loginUser(usersRequest, usersResponse, request);
        return ResponseEntity.ok(usersResponse);
    }

    // ユーザーログアウト
    @DeleteMapping("/credentials")
    public ResponseEntity<?> logoutUser(HttpServletRequest request) {
        if (!loginChecker.checkLogin(request)) {
            return ResponseEntity.status(401).body("認証が必要です");
        }
        usersService.logoutUser(request);
        return ResponseEntity.ok().build();
    }

}
