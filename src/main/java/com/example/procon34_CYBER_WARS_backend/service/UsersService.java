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

    private UsersMapper usersMapper;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UsersMapper usersMapper, PasswordEncoder passwordEncoder) {
        this.usersMapper = usersMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // ユーザー登録
    public UsersResponse register(UsersRequest usersRequest, UsersResponse usersResponse) {
        Users users = usersMapper.search(usersRequest);
        if (users == null) {
            String hashedPassword = passwordEncoder.encodePassword(usersRequest.getPassword());
            usersRequest.setPassword(hashedPassword);
            usersMapper.register(usersRequest);
            usersResponse.setSuccess(true);
        } else {
            usersResponse.setSuccess(false);
        }
        return usersResponse;
    }

    // ユーザー情報変更
    public UsersResponse update(UsersUpdateRequest usersUpdateRequest, UsersResponse usersResponse,
            HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        usersUpdateRequest.setName(usersUpdateRequest.getName().trim());
        usersUpdateRequest.setPassword(usersUpdateRequest.getPassword().trim());
        if (!usersUpdateRequest.getName().isEmpty()) {
            UsersRequest usersRequest = new UsersRequest();
            usersRequest.setName(usersUpdateRequest.getName());
            Users users = usersMapper.search(usersRequest);
            if (users == null) {
                usersUpdateRequest.setUserId((Long) session.getAttribute("key"));
                usersMapper.updateName(usersUpdateRequest);
                usersResponse.setSuccess(true);
                System.out.println("a");
            } else {
                usersResponse.setSuccess(false);
            }
        } else if (!usersUpdateRequest.getPassword().isEmpty()) {
            String hashedPassword = passwordEncoder.encodePassword(usersUpdateRequest.getPassword());
            usersUpdateRequest.setPassword(hashedPassword);
            usersMapper.updatePassword(usersUpdateRequest);
            usersResponse.setSuccess(true);
            System.out.println("b");
        } else {
            usersResponse.setSuccess(false);
        }
        return usersResponse;
    }

    // ユーザーログイン
    public UsersResponse login(UsersRequest usersRequest, UsersResponse usersResponse, HttpServletRequest request) {
        Users users = usersMapper.search(usersRequest);
        if (users != null && passwordEncoder.checkPassword(usersRequest.getPassword(), users.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("key", users.getUserId());
            session.setMaxInactiveInterval(20);
            usersResponse.setSuccess(true);
        } else {
            usersResponse.setSuccess(false);
        }
        return usersResponse;
    }

    // ユーザーログアウト

}
