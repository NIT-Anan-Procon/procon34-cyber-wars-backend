package com.example.procon34_CYBER_WARS_backend.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.procon34_CYBER_WARS_backend.dto.utility.GetUserByNameRequest;
import com.example.procon34_CYBER_WARS_backend.entity.Users;
import com.example.procon34_CYBER_WARS_backend.mapper.UtilityMapper;

@Component
public class UserGetterByName {

    private final UtilityMapper utilityMapper;

    @Autowired
    public UserGetterByName(final UtilityMapper utilityMapper) {
        this.utilityMapper = utilityMapper;
    }

    private final GetUserByNameRequest getUserByNameRequest = new GetUserByNameRequest();

    // ユーザー取得 by ユーザー名
    public Users getUserByName(final String name) {
        getUserByNameRequest.setName(name);
        final Users users = utilityMapper.getUserByName(getUserByNameRequest);
        return users;
    }

}
