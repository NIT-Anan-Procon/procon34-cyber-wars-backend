package com.example.procon34_CYBER_WARS_backend.repository;

import org.springframework.stereotype.Repository;

import com.example.procon34_CYBER_WARS_backend.entity.Users;
import com.example.procon34_CYBER_WARS_backend.mapper.UsersMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UsersRepository {

    private final UsersMapper usersMapper;

    // ユーザー登録
    public void register(final String name, final String password) {
        usersMapper.register(name, password);
    }

    // ユーザー取得 by ユーザーID
    public Users fetchUserByUserId(final int userId) {
        return usersMapper.fetchUserByUserId(userId);
    }

    // ユーザー取得 by ユーザー名
    public Users fetchUserByName(final String name) {
        return usersMapper.fetchUserByName(name);
    }

    // ユーザー名更新
    public void updateName(final int userId, final String name) {
        usersMapper.updateName(userId, name);
    }

    // ユーザーパスワード更新
    public void updatePassword(final int userId, final String password) {
        usersMapper.updatePassword(userId, password);
    }

}