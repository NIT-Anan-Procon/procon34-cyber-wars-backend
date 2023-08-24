package com.example.procon34_CYBER_WARS_backend.utility;

import org.springframework.stereotype.Component;

import com.example.procon34_CYBER_WARS_backend.dto.utility.GetUserByNameRequest;
import com.example.procon34_CYBER_WARS_backend.entity.Users;
import com.example.procon34_CYBER_WARS_backend.repository.UtilityRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserGetterByName {

    private final UtilityRepository utilityRepository;

    private final GetUserByNameRequest getUserByNameRequest = new GetUserByNameRequest();

    // ユーザー取得 by ユーザー名
    public Users getUserByName(final String name) {
        getUserByNameRequest.setName(name);
        final Users users = utilityRepository.getUserByName(getUserByNameRequest);
        return users;
    }

}
