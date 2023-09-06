package com.example.procon34_CYBER_WARS_backend.utility.users;

import org.springframework.stereotype.Component;

import com.example.procon34_CYBER_WARS_backend.entity.Users;
import com.example.procon34_CYBER_WARS_backend.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserFetcherByName {

    private final UsersRepository usersRepository;

    // ユーザー取得 by ユーザー名
    public Users fetchUserByName(final String name) {
        return usersRepository.fetchUserByName(name);
    }

}
