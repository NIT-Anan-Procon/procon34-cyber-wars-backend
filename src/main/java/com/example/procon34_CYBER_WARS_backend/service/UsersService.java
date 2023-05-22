package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.procon34_CYBER_WARS_backend.dto.UsersCredentialsRequest;
import com.example.procon34_CYBER_WARS_backend.entity.Users;
import com.example.procon34_CYBER_WARS_backend.repository.UsersMapper;
import com.example.procon34_CYBER_WARS_backend.util.PasswordEncoder;

@Service
public class UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(UsersCredentialsRequest usersCredentialsRequest) {
        String hashedPassword = passwordEncoder
                .encodePassword(usersCredentialsRequest.getName() + usersCredentialsRequest.getPassword());
        usersCredentialsRequest.setPassword(hashedPassword);
        usersMapper.register(usersCredentialsRequest);
    }

    public void login(UsersCredentialsRequest usersCredentialsRequest) {
        String hashedPassword = passwordEncoder
                .encodePassword(usersCredentialsRequest.getName() + usersCredentialsRequest.getPassword());
        usersCredentialsRequest.setPassword(hashedPassword);
        Users users = usersMapper.login(usersCredentialsRequest);
        if (users != null) {
            System.out.println("login");
        } else {
            System.out.println("failed");
        }
    }

}
