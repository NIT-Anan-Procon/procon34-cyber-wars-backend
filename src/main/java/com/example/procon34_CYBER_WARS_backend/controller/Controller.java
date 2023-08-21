package com.example.procon34_CYBER_WARS_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.procon34_CYBER_WARS_backend.dto.Users.RegisterUserRequest;
import com.example.procon34_CYBER_WARS_backend.dto.Users.UpdateUserNameRequest;
import com.example.procon34_CYBER_WARS_backend.dto.Users.UpdateUserPasswordRequest;
import com.example.procon34_CYBER_WARS_backend.service.UsersService;
import com.example.procon34_CYBER_WARS_backend.util.LoginChecker;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class Controller {

    private final UsersService usersService;
    private final LoginChecker loginChecker;

    @Autowired
    public Controller(UsersService usersService, LoginChecker loginChecker) {
        this.usersService = usersService;
        this.loginChecker = loginChecker;
    }

    // ユーザー登録
    @PutMapping
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserRequest registerUserRequest,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        return ResponseEntity.ok(usersService.registerUser(registerUserRequest));
    }

    // ユーザー名更新
    @PatchMapping("/name")
    public ResponseEntity<?> updateUserName(@Valid @RequestBody UpdateUserNameRequest updateUserNameRequest,
            BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        if (!loginChecker.checkLogin(httpServletRequest)) {
            return ResponseEntity.status(401).body("認証が必要です");
        }
        return ResponseEntity.ok(usersService.updateUserName(updateUserNameRequest, httpServletRequest));
    }

    // ユーザーパスワード更新
    @PatchMapping("/password")
    public ResponseEntity<?> updateUserPassword(@Valid @RequestBody UpdateUserPasswordRequest updateUserPasswordRequest,
            BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        if (!loginChecker.checkLogin(httpServletRequest)) {
            return ResponseEntity.status(401).body("認証が必要です");
        }
        return ResponseEntity.ok(usersService.updateUserPassword(updateUserPasswordRequest, httpServletRequest));
    }

}
