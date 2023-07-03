package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.procon34_CYBER_WARS_backend.dto.UsersCredentialsRequest;
import com.example.procon34_CYBER_WARS_backend.dto.UsersCredentialsResponse;
import com.example.procon34_CYBER_WARS_backend.entity.Users;
import com.example.procon34_CYBER_WARS_backend.repository.UsersMapper;
import com.example.procon34_CYBER_WARS_backend.util.PasswordEncoder;

@Service
public class UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    // ユーザー登録
    public UsersCredentialsResponse register(UsersCredentialsRequest usersCredentialsRequest,
            UsersCredentialsResponse usersCredentialsResponse) {
        Users users = usersMapper.search(usersCredentialsRequest);
        if (users == null) {
            String hashedPassword = passwordEncoder.encodePassword(usersCredentialsRequest.getPassword());
            usersCredentialsRequest.setPassword(hashedPassword);
            usersMapper.register(usersCredentialsRequest);
            usersCredentialsResponse.setSuccess(true);
        } else {
            usersCredentialsResponse.setSuccess(false);
        }
        return usersCredentialsResponse;
    }

    // ユーザー情報変更

    // ユーザーログイン
    public UsersCredentialsResponse login(UsersCredentialsRequest usersCredentialsRequest,
            UsersCredentialsResponse usersCredentialsResponse) {
        Users users = usersMapper.search(usersCredentialsRequest);
        if (users != null
                && passwordEncoder.checkPassword(usersCredentialsRequest.getPassword(), users.getPassword())) {
            usersCredentialsResponse.setSuccess(true);
        } else {
            usersCredentialsResponse.setSuccess(false);
        }
        return usersCredentialsResponse;
    }

    // ユーザーログアウト

}
