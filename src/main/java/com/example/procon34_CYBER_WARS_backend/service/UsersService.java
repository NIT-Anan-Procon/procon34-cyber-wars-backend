package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.procon34_CYBER_WARS_backend.dto.UsersRequest;
import com.example.procon34_CYBER_WARS_backend.dto.UsersResponse;
import com.example.procon34_CYBER_WARS_backend.dto.UsersUpdateRequest;
import com.example.procon34_CYBER_WARS_backend.entity.Users;
import com.example.procon34_CYBER_WARS_backend.repository.UsersMapper;
import com.example.procon34_CYBER_WARS_backend.util.PasswordEncoder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class UsersService {

    private final UsersMapper usersMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UsersMapper usersMapper, PasswordEncoder passwordEncoder) {
        this.usersMapper = usersMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // ユーザー登録
    public UsersResponse registerUser(UsersRequest usersRequest, UsersResponse usersResponse) {
        Users users = usersMapper.searchUserByName(usersRequest);
        // ユーザーが存在する場合
        if (users != null) {
            usersResponse.setSuccess(false);
            return usersResponse;
        }
        String hashedPassword = passwordEncoder.encodePassword(usersRequest.getPassword());
        usersRequest.setPassword(hashedPassword);
        usersMapper.registerUser(usersRequest);
        usersResponse.setSuccess(true);
        return usersResponse;
    }

    // ユーザー名変更
    public UsersResponse updateUserName(UsersUpdateRequest usersUpdateRequest, UsersResponse usersResponse,
            HttpServletRequest request) {
        usersUpdateRequest.setName(usersUpdateRequest.getName().trim());
        // ユーザー名が空の場合
        if (usersUpdateRequest.getName().isEmpty()) {
            usersResponse.setSuccess(false);
            return usersResponse;
        }
        UsersRequest usersRequest = new UsersRequest();
        usersRequest.setName(usersUpdateRequest.getName());
        Users users = usersMapper.searchUserByName(usersRequest);
        // ユーザーが存在する場合
        if (users != null) {
            usersResponse.setSuccess(false);
            return usersResponse;
        }
        HttpSession session = request.getSession(false);
        usersUpdateRequest.setUserId((Long) session.getAttribute("sessionId"));
        usersMapper.updateUserName(usersUpdateRequest);
        usersResponse.setSuccess(true);
        return usersResponse;
    }

    // ユーザーパスワード変更
    public UsersResponse updateUserPassword(UsersUpdateRequest usersUpdateRequest, UsersResponse usersResponse,
            HttpServletRequest request) {
        usersUpdateRequest.setPassword(usersUpdateRequest.getPassword().trim());
        // ユーザーパスワードが空の場合
        if (usersUpdateRequest.getPassword().isEmpty()) {
            usersResponse.setSuccess(false);
            return usersResponse;
        }
        HttpSession session = request.getSession(false);
        usersUpdateRequest.setUserId((Long) session.getAttribute("sessionId"));
        String hashedPassword = passwordEncoder.encodePassword(usersUpdateRequest.getPassword());
        usersUpdateRequest.setPassword(hashedPassword);
        usersMapper.updateUserPassword(usersUpdateRequest);
        usersResponse.setSuccess(true);
        return usersResponse;
    }

    // ユーザーログイン
    public UsersResponse loginUser(UsersRequest usersRequest, UsersResponse usersResponse, HttpServletRequest request) {
        Users users = usersMapper.searchUserByName(usersRequest);
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
