package com.example.procon34_CYBER_WARS_backend.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.procon34_CYBER_WARS_backend.dto.utilities.SearchUserByNameRequest;
import com.example.procon34_CYBER_WARS_backend.entity.Users;
import com.example.procon34_CYBER_WARS_backend.repository.UtilitiesMapper;

@Component
public class UserSearcherByName {

    private final UtilitiesMapper utilitiesMapper;

    @Autowired
    public UserSearcherByName(UtilitiesMapper utilitiesMapper) {
        this.utilitiesMapper = utilitiesMapper;
    }

    private final SearchUserByNameRequest searchUserByNameRequest = new SearchUserByNameRequest();

    public Users searchUserByName(String name) {
        searchUserByNameRequest.setName(name);
        Users users = utilitiesMapper.searchUserByName(searchUserByNameRequest);
        return users;
    }

}
