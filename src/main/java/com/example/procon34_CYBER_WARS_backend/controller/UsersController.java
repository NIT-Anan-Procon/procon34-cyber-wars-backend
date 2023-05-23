package com.example.procon34_CYBER_WARS_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.procon34_CYBER_WARS_backend.dto.UsersCredentialsRequest;
import com.example.procon34_CYBER_WARS_backend.dto.UsersLoginResponse;
import com.example.procon34_CYBER_WARS_backend.service.UsersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @Autowired
    UsersLoginResponse usersLoginResponse;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UsersCredentialsRequest usersCredentialsRequest,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        } else {
            usersService.register(usersCredentialsRequest);
            return ResponseEntity.ok("Success");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UsersCredentialsRequest usersCredentialsRequest,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        } else {
            UsersLoginResponse usersLoginResponse = usersService.login(usersCredentialsRequest);
            return ResponseEntity.ok(usersLoginResponse);
        }
    }

}
