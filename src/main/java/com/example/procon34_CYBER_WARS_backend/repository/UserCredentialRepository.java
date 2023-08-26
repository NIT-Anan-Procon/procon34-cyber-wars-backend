package com.example.procon34_CYBER_WARS_backend.repository;

import org.springframework.stereotype.Repository;

import com.example.procon34_CYBER_WARS_backend.entity.Users;
import com.example.procon34_CYBER_WARS_backend.mapper.UserCredentialMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserCredentialRepository {

    private final UserCredentialMapper userCredentialMapper;

    // ユーザー取得 by ユーザーID
    public Users getUserByUserId(final int userId) {
        return userCredentialMapper.getUserByUserId(userId);
    }

}
