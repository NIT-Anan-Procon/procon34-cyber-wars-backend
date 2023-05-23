package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.procon34_CYBER_WARS_backend.dto.UsersRegisterRequest;
import com.example.procon34_CYBER_WARS_backend.repository.UsersMapper;
import com.example.procon34_CYBER_WARS_backend.util.PasswordEncoder;

@Service
public class UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(UsersRegisterRequest usersRegisterRequest) {
        String hashedPassword = passwordEncoder.encodePassword(usersRegisterRequest.getPassword());
        usersRegisterRequest.setPassword(hashedPassword);
        usersMapper.register(usersRegisterRequest);
    }

}
