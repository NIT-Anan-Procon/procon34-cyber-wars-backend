package com.example.procon34_CYBER_WARS_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.procon34_CYBER_WARS_backend.dto.UsersCredentialsRequest;
import com.example.procon34_CYBER_WARS_backend.dto.UsersCredentialsResponse;
import com.example.procon34_CYBER_WARS_backend.service.UsersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    private UsersCredentialsResponse usersCredentialsResponse = new UsersCredentialsResponse();

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UsersCredentialsRequest usersCredentialsRequest,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        } else {
            usersCredentialsResponse = usersService.register(usersCredentialsRequest, usersCredentialsResponse);
            return ResponseEntity.ok(usersCredentialsResponse);
        }
    }

}
