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
    public void registerUser(final Users user) {
        usersMapper.registerUser(user);
    }

    // ユーザー名更新
    public void updateName(final Users user) {
        usersMapper.updateName(user);
    }

    // ユーザーパスワード更新
    public void updatePassword(final Users user) {
        usersMapper.updatePassword(user);
    }

}
