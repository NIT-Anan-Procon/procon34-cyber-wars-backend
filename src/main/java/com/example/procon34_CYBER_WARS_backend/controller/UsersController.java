package com.example.procon34_CYBER_WARS_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.procon34_CYBER_WARS_backend.dto.users.RegisterUserRequest;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdateNameRequest;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdatePasswordRequest;
import com.example.procon34_CYBER_WARS_backend.service.UsersService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(final UsersService usersService) {
        this.usersService = usersService;
    }

    // ユーザー登録
    @PostMapping
    public ResponseEntity<?> registerUser(@Valid @RequestBody final RegisterUserRequest registerUserRequest,
            final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        return ResponseEntity.ok(usersService.registerUser(registerUserRequest));
    }

    // ユーザー名更新
    @PatchMapping("/name")
    public ResponseEntity<?> updateName(@Valid @RequestBody final UpdateNameRequest updateNameRequest,
            final BindingResult bindingResult, final HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        return ResponseEntity.ok(usersService.updateName(updateNameRequest, httpServletRequest));
    }

    // ユーザーパスワード更新
    @PatchMapping("/password")
    public ResponseEntity<?> updatePassword(@Valid @RequestBody final UpdatePasswordRequest updatePasswordRequest,
            final BindingResult bindingResult, final HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        return ResponseEntity.ok(usersService.updatePassword(updatePasswordRequest, httpServletRequest));
    }

}
