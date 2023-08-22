package com.example.procon34_CYBER_WARS_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    // ユーザー登録
    @PostMapping
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
            BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        UpdateUserNameResponse updateUserNameResponse = usersService.updateUserName(updateUserNameRequest,
                httpServletRequest);
        return ResponseEntity.ok(updateUserNameResponse);
    }

    // ユーザーパスワード更新
    @PatchMapping("/password")
    public ResponseEntity<?> updateUserPassword(@Valid @RequestBody UpdateUserPasswordRequest updateUserPasswordRequest,
            BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        UpdateUserPasswordResponse updateUserPasswordResponse = usersService
                .updateUserPassword(updateUserPasswordRequest, httpServletRequest);
        return ResponseEntity.ok(updateUserPasswordResponse);
    }

}
