package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.procon34_CYBER_WARS_backend.dto.Users.SearchUserByNameRequest;
import com.example.procon34_CYBER_WARS_backend.dto.Users.Credentials.LoginUserRequest;
import com.example.procon34_CYBER_WARS_backend.dto.Users.Credentials.LoginUserResponse;
import com.example.procon34_CYBER_WARS_backend.entity.Users;
import com.example.procon34_CYBER_WARS_backend.repository.UsersMapper;
import com.example.procon34_CYBER_WARS_backend.util.PasswordEncoder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class UsersCredentialsService {

    private final UsersMapper usersMapper;
    private final SearchUserByNameRequest searchUserByNameRequest;
    private final LoginUserResponse loginUserResponse;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersCredentialsService(UsersMapper usersMapper, SearchUserByNameRequest searchUserByNameRequest,
            LoginUserResponse loginUserResponse, PasswordEncoder passwordEncoder) {
        this.usersMapper = usersMapper;
        this.searchUserByNameRequest = searchUserByNameRequest;
        this.loginUserResponse = loginUserResponse;
        this.passwordEncoder = passwordEncoder;
    }

    // ユーザーログイン
    public LoginUserResponse loginUser(LoginUserRequest loginUserRequest, HttpServletRequest httpServletRequest) {
        Users users = usersMapper.searchUserByName(searchUserByNameRequest);
        // ユーザーが存在しない場合
        if (users == null) {
            loginUserResponse.setSuccess(false);
            return loginUserResponse;
        }
        // ユーザーパスワードが異なる場合
        if (!passwordEncoder.checkPassword(loginUserRequest.getPassword(), users.getPassword())) {
            loginUserResponse.setSuccess(false);
            return loginUserResponse;
        }
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("sessionId", users.getUserId());
        httpSession.setMaxInactiveInterval(60 * 60);
        loginUserResponse.setSuccess(true);
        return loginUserResponse;
    }

    // ユーザーログアウト
    public void logoutUser(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession(false);
        httpSession.invalidate();
    }

}
