package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.procon34_CYBER_WARS_backend.dto.UsersCredentialsRequest;
import com.example.procon34_CYBER_WARS_backend.dto.UsersLoginResponse;
import com.example.procon34_CYBER_WARS_backend.entity.Users;
import com.example.procon34_CYBER_WARS_backend.repository.UsersMapper;
import com.example.procon34_CYBER_WARS_backend.util.PasswordEncoder;

@Service
public class UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void register(UsersCredentialsRequest usersCredentialsRequest) {
        String hashedPassword = passwordEncoder.encodePassword(usersCredentialsRequest.getPassword());
        usersCredentialsRequest.setPassword(hashedPassword);
        usersMapper.register(usersCredentialsRequest);
    }

    public UsersLoginResponse login(UsersCredentialsRequest usersCredentialsRequest,
            UsersLoginResponse usersLoginResponse) {
        Users users = usersMapper.login(usersCredentialsRequest);
        if (users != null
                && passwordEncoder.checkPassword(usersCredentialsRequest.getPassword(), users.getPassword())) {
            usersLoginResponse.setSuccess(true);
        } else {
            usersLoginResponse.setSuccess(false);
        }
        return usersLoginResponse;
    }

}
