package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.procon34_CYBER_WARS_backend.dto.Users.RegisterUserRequest;
import com.example.procon34_CYBER_WARS_backend.dto.Users.SearchUserByNameRequest;
import com.example.procon34_CYBER_WARS_backend.dto.Users.UpdateUserNameResponse;
import com.example.procon34_CYBER_WARS_backend.entity.Users;
import com.example.procon34_CYBER_WARS_backend.repository.UsersMapper;
import com.example.procon34_CYBER_WARS_backend.util.PasswordEncoder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class UsersCredentialsService {

    private final UsersMapper usersMapper;
    private final SearchUserByNameRequest searchUserByNameRequest;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersCredentialsService(UsersMapper usersMapper, SearchUserByNameRequest searchUserByNameRequest,
            PasswordEncoder passwordEncoder) {
        this.usersMapper = usersMapper;
        this.searchUserByNameRequest = searchUserByNameRequest;
        this.passwordEncoder = passwordEncoder;
    }

    // ユーザーログイン
    public UpdateUserNameResponse loginUser(RegisterUserRequest usersRequest, UpdateUserNameResponse usersResponse,
            HttpServletRequest request) {
        Users users = usersMapper.searchUserByName(searchUserByNameRequest);
        // ユーザーが存在しない場合
        if (users == null) {
            usersResponse.setSuccess(false);
            return usersResponse;
        }
        // ユーザーパスワードが異なる場合
        if (!passwordEncoder.checkPassword(usersRequest.getPassword(), users.getPassword())) {
            usersResponse.setSuccess(false);
            return usersResponse;
        }
        HttpSession session = request.getSession();
        session.setAttribute("sessionId", users.getUserId());
        session.setMaxInactiveInterval(60 * 60);
        usersResponse.setSuccess(true);
        return usersResponse;
    }

    // ユーザーログアウト
    public void logoutUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.invalidate();
    }

}
