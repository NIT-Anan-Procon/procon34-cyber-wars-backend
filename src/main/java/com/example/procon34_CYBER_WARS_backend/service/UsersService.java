package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.stereotype.Service;

import com.example.procon34_CYBER_WARS_backend.dto.users.RegisterUserRequest;
import com.example.procon34_CYBER_WARS_backend.dto.users.RegisterUserResponse;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdateNameRequest;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdateNameResponse;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdatePasswordRequest;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdatePasswordResponse;
import com.example.procon34_CYBER_WARS_backend.entity.Users;
import com.example.procon34_CYBER_WARS_backend.mapper.UsersMapper;
import com.example.procon34_CYBER_WARS_backend.utility.PasswordEncoder;
import com.example.procon34_CYBER_WARS_backend.utility.StringFormatter;
import com.example.procon34_CYBER_WARS_backend.utility.UserGetterByName;
import com.example.procon34_CYBER_WARS_backend.utility.UserIdGetter;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersMapper usersMapper;
    private final StringFormatter stringFormatter;
    private final UserGetterByName userGetterByName;
    private final PasswordEncoder passwordEncoder;
    private final UserIdGetter userIdGetter;

    // @Autowired
    // public UsersService(final UsersMapper usersMapper, final StringFormatter
    // stringFormatter,
    // final UserGetterByName userGetterByName, final PasswordEncoder
    // passwordEncoder,
    // final UserIdGetter userIdGetter) {
    // this.usersMapper = usersMapper;
    // this.stringFormatter = stringFormatter;
    // this.userGetterByName = userGetterByName;
    // this.passwordEncoder = passwordEncoder;
    // this.userIdGetter = userIdGetter;
    // }

    private final RegisterUserResponse registerUserResponse = new RegisterUserResponse();
    private final UpdateNameResponse updateNameResponse = new UpdateNameResponse();
    private final UpdatePasswordResponse updatePasswordResponse = new UpdatePasswordResponse();

    // ユーザー登録
    public RegisterUserResponse registerUser(final RegisterUserRequest registerUserRequest) {
        final String formattedName = stringFormatter.formatString(registerUserRequest.getName());
        registerUserRequest.setName(formattedName);

        // ユーザー名が空の場合
        if (registerUserRequest.getName().isEmpty()) {
            updateNameResponse.setSuccess(false);
            return registerUserResponse;
        }

        final Users users = userGetterByName.getUserByName(registerUserRequest.getName());

        // ユーザーが存在する場合
        if (users != null) {
            registerUserResponse.setSuccess(false);
            return registerUserResponse;
        }

        final String formattedPassword = stringFormatter.formatString(registerUserRequest.getPassword());
        registerUserRequest.setPassword(formattedPassword);

        // ユーザーパスワードが空の場合
        if (registerUserRequest.getPassword().isEmpty()) {
            registerUserResponse.setSuccess(false);
            return registerUserResponse;
        }

        final String hashedPassword = passwordEncoder.encodePassword(registerUserRequest.getPassword());
        registerUserRequest.setPassword(hashedPassword);

        usersMapper.registerUser(registerUserRequest);

        registerUserResponse.setSuccess(true);
        return registerUserResponse;
    }

    // ユーザー名更新
    public UpdateNameResponse updateName(final UpdateNameRequest updateNameRequest,
            final HttpServletRequest httpServletRequest) {
        final String formattedName = stringFormatter.formatString(updateNameRequest.getName());
        updateNameRequest.setName(formattedName);

        // ユーザー名が空の場合
        if (updateNameRequest.getName().isEmpty()) {
            updateNameResponse.setSuccess(false);
            return updateNameResponse;
        }

        final Users users = userGetterByName.getUserByName(updateNameRequest.getName());

        // ユーザーが存在する場合
        if (users != null) {
            updateNameResponse.setSuccess(false);
            return updateNameResponse;
        }

        final int userId = userIdGetter.getUserId(httpServletRequest);
        updateNameRequest.setUser_id(userId);

        usersMapper.updateName(updateNameRequest);

        updateNameResponse.setSuccess(true);
        return updateNameResponse;
    }

    // ユーザーパスワード更新
    public UpdatePasswordResponse updatePassword(final UpdatePasswordRequest updatePasswordRequest,
            final HttpServletRequest httpServletRequest) {
        final String formattedString = stringFormatter.formatString(updatePasswordRequest.getPassword());
        updatePasswordRequest.setPassword(formattedString);

        // ユーザーパスワードが空の場合
        if (updatePasswordRequest.getPassword().isEmpty()) {
            updatePasswordResponse.setSuccess(false);
            return updatePasswordResponse;
        }

        final int userId = userIdGetter.getUserId(httpServletRequest);
        updatePasswordRequest.setUser_id(userId);

        final String hashedPassword = passwordEncoder.encodePassword(updatePasswordRequest.getPassword());
        updatePasswordRequest.setPassword(hashedPassword);

        usersMapper.updatePassword(updatePasswordRequest);

        updatePasswordResponse.setSuccess(true);
        return updatePasswordResponse;
    }

}
