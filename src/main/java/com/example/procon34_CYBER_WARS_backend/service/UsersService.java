package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.procon34_CYBER_WARS_backend.dto.Users.RegisterUserRequest;
import com.example.procon34_CYBER_WARS_backend.dto.Users.RegisterUserResponse;
import com.example.procon34_CYBER_WARS_backend.dto.Users.SearchUserByNameRequest;
import com.example.procon34_CYBER_WARS_backend.dto.Users.UpdateUserNameResponse;
import com.example.procon34_CYBER_WARS_backend.dto.Users.UpdateUserPasswordResponse;
import com.example.procon34_CYBER_WARS_backend.entity.Users;
import com.example.procon34_CYBER_WARS_backend.repository.UsersMapper;
import com.example.procon34_CYBER_WARS_backend.util.PasswordEncoder;

@Service
public class UsersService {

    private final UsersMapper usersMapper;
    private final SearchUserByNameRequest searchUserByNameRequest;
    private final RegisterUserResponse registerUserResponse;
    private final UpdateUserNameResponse updateUserNameResponse;
    private final UpdateUserPasswordResponse updateUserPasswordResponse;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UsersMapper usersMapper, SearchUserByNameRequest searchUserByNameRequest,
            RegisterUserResponse registerUserResponse, UpdateUserNameResponse updateUserNameResponse,
            UpdateUserPasswordResponse updateUserPasswordResponse, PasswordEncoder passwordEncoder) {
        this.usersMapper = usersMapper;
        this.searchUserByNameRequest = searchUserByNameRequest;
        this.registerUserResponse = registerUserResponse;
        this.updateUserNameResponse = updateUserNameResponse;
        this.updateUserPasswordResponse = updateUserPasswordResponse;
        this.passwordEncoder = passwordEncoder;
    }

    // ユーザー登録
    public RegisterUserResponse registerUser(RegisterUserRequest registerUsersRequest) {
        registerUsersRequest.setName(registerUsersRequest.getName().trim());
        searchUserByNameRequest.setName(registerUsersRequest.getName());
        Users users = usersMapper.searchUserByName(searchUserByNameRequest);
        // ユーザーが存在する場合
        if (users != null) {
            registerUserResponse.setSuccess(false);
            return registerUserResponse;
        }
        registerUsersRequest.setPassword(registerUsersRequest.getPassword().trim());
        String hashedPassword = passwordEncoder.encodePassword(registerUsersRequest.getPassword());
        registerUsersRequest.setPassword(hashedPassword);
        usersMapper.registerUser(registerUsersRequest);
        registerUserResponse.setSuccess(true);
        return registerUserResponse;
    }

    // ユーザー名更新
    // public UpdateUserNameResponse updateUserName(UpdateUserNameRequest
    // updateUserNameRequest,
    // HttpServletRequest httpServletRequest) {
    // updateUserNameRequest.setName(updateUserNameRequest.getName().trim());
    // // ユーザー名が空の場合
    // if (updateUserNameRequest.getName().isEmpty()) {
    // updateUserNameResponse.setSuccess(false);
    // return updateUserNameResponse;
    // }
    // searchUserByNameRequest.setName(updateUserNameRequest.getName());
    // Users users = usersMapper.searchUserByName(searchUserByNameRequest);
    // // ユーザーが存在する場合
    // if (users != null) {
    // updateUserNameResponse.setSuccess(false);
    // return updateUserNameResponse;
    // }
    // HttpSession httpSession = httpServletRequest.getSession(false);
    // updateUserNameRequest.setUserId((Long)
    // httpSession.getAttribute("sessionId"));
    // usersMapper.updateUserName(updateUserNameRequest);
    // updateUserNameResponse.setSuccess(true);
    // return updateUserNameResponse;
    // }

    // // ユーザーパスワード更新
    // public UpdateUserPasswordResponse
    // updateUserPassword(UpdateUserPasswordRequest updateUserPasswordRequest,
    // HttpServletRequest httpServletRequest) {
    // updateUserPasswordRequest.setPassword(updateUserPasswordRequest.getPassword().trim());
    // // ユーザーパスワードが空の場合
    // if (updateUserPasswordRequest.getPassword().isEmpty()) {
    // updateUserPasswordResponse.setSuccess(false);
    // return updateUserPasswordResponse;
    // }
    // HttpSession httpSession = httpServletRequest.getSession(false);
    // updateUserPasswordRequest.setUserId((Long)
    // httpSession.getAttribute("sessionId"));
    // String hashedPassword =
    // passwordEncoder.encodePassword(updateUserPasswordRequest.getPassword());
    // updateUserPasswordRequest.setPassword(hashedPassword);
    // usersMapper.updateUserPassword(updateUserPasswordRequest);
    // updateUserPasswordResponse.setSuccess(true);
    // return updateUserPasswordResponse;
    // }

}
