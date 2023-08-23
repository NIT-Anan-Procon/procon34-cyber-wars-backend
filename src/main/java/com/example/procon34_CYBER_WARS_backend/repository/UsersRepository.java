package com.example.procon34_CYBER_WARS_backend.repository;

import org.springframework.stereotype.Repository;

import com.example.procon34_CYBER_WARS_backend.dto.users.RegisterUserRequest;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdateNameRequest;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdatePasswordRequest;
import com.example.procon34_CYBER_WARS_backend.mapper.UsersMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UsersRepository {

    private final UsersMapper usersMapper;

    // ユーザー登録
    public void registerUser(final RegisterUserRequest registerUserRequest) {
        usersMapper.registerUser(registerUserRequest);
    }

    // ユーザー名更新
    public void updateName(final UpdateNameRequest updateNameRequest) {
        usersMapper.updateName(updateNameRequest);
    }

    // ユーザーパスワード更新
    public void updatePassword(final UpdatePasswordRequest updatePasswordRequest) {
        usersMapper.updatePassword(updatePasswordRequest);
    }

}
