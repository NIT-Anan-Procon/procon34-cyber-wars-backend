package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.procon34_CYBER_WARS_backend.dto.UsersRegisterRequest;
import com.example.procon34_CYBER_WARS_backend.repository.UsersMapper;

@Service
public class UsersService {

    @Autowired
    private UsersMapper usersMapper;

    public void register(UsersRegisterRequest usersRegisterRequest) {
        usersMapper.register(usersRegisterRequest);
    }

}
