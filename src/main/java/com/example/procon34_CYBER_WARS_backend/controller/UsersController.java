package com.example.procon34_CYBER_WARS_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.procon34_CYBER_WARS_backend.dto.UsersRegisterRequest;
import com.example.procon34_CYBER_WARS_backend.service.UsersService;

@RestController
public class UsersController {

    @Autowired
    UsersService usersService;

    @RequestMapping(value = "/register-user", method = RequestMethod.POST)
    public void registerUser(@ModelAttribute UsersRegisterRequest usersRegisterRequest) {
        usersService.registerUser(usersRegisterRequest);
    }

}
