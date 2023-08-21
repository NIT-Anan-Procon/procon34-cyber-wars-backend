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
import com.example.procon34_CYBER_WARS_backend.dto.Users.RegisterUserResponse;
import com.example.procon34_CYBER_WARS_backend.dto.Users.UpdateUserNameRequest;
import com.example.procon34_CYBER_WARS_backend.dto.Users.UpdateUserNameResponse;
import com.example.procon34_CYBER_WARS_backend.dto.Users.UpdateUserPasswordRequest;
import com.example.procon34_CYBER_WARS_backend.dto.Users.UpdateUserPasswordResponse;
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

    // ユーザー登録
    @PutMapping
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserRequest registerUserRequest,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        RegisterUserResponse registerUserResponse = usersService.registerUser(registerUserRequest);
        return ResponseEntity.ok(registerUserResponse);
    }

    // ユーザー名更新
    @PatchMapping("/name")
    public ResponseEntity<?> updateUserName(@Valid @RequestBody UpdateUserNameRequest updateUserNameRequest,
            HttpServletRequest httpServletRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        if (!loginChecker.checkLogin(httpServletRequest)) {
            return ResponseEntity.status(401).body("認証が必要です");
        }
        UpdateUserNameResponse updateUserNameResponse = usersService.updateUserName(updateUserNameRequest,
                httpServletRequest);
        return ResponseEntity.ok(updateUserNameResponse);
    }

    // ユーザーパスワード更新
    @PatchMapping("/password")
    public ResponseEntity<?> updateUserPassword(@Valid @RequestBody UpdateUserPasswordRequest updateUserPasswordRequest,
            HttpServletRequest httpServletRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        if (!loginChecker.checkLogin(httpServletRequest)) {
            return ResponseEntity.status(401).body("認証が必要です");
        }
        UpdateUserPasswordResponse updateUserPasswordResponse = usersService
                .updateUserPassword(updateUserPasswordRequest, httpServletRequest);
        return ResponseEntity.ok(updateUserPasswordResponse);
    }

}
