package com.example.procon34_CYBER_WARS_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.procon34_CYBER_WARS_backend.dto.UsersRegisterRequest;
import com.example.procon34_CYBER_WARS_backend.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @PostMapping("/register-user")
    public void registerUser(@RequestBody UsersRegisterRequest usersRegisterRequest) {
        usersService.registerUser(usersRegisterRequest);
    }

}
