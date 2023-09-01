package com.example.procon34_CYBER_WARS_backend.repository;

import org.springframework.stereotype.Repository;

import com.example.procon34_CYBER_WARS_backend.entity.Users;
import com.example.procon34_CYBER_WARS_backend.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final UserMapper userMapper;

    // ユーザー取得 by ユーザー名
    public Users getUserByName(final String name) {
        return userMapper.getUserByName(name);
    }

    // ユーザー登録
    public void register(final String name, final String password) {
        userMapper.register(name, password);
    }

    // ユーザー名更新
    public void updateName(final int userId, final String name) {
        userMapper.updateName(userId, name);
    }

    // ユーザーパスワード更新
    public void updatePassword(final int userId, final String password) {
        userMapper.updatePassword(userId, password);
    }

}
