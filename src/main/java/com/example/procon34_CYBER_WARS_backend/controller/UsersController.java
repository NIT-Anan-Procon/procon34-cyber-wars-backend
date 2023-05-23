package com.example.procon34_CYBER_WARS_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.procon34_CYBER_WARS_backend.dto.UsersCredentialsRequest;
import com.example.procon34_CYBER_WARS_backend.service.UsersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UsersCredentialsRequest usersRegisterRequest,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        } else {
            usersService.register(usersRegisterRequest);
            return ResponseEntity.ok("Success");
        }
    }

}
