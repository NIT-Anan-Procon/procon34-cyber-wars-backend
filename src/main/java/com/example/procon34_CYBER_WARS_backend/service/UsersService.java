package com.example.procon34_CYBER_WARS_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.procon34_CYBER_WARS_backend.dto.users.RegisterUserRequest;
import com.example.procon34_CYBER_WARS_backend.dto.users.RegisterUserResponse;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdateNameRequest;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdateNameResponse;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdatePasswordRequest;
import com.example.procon34_CYBER_WARS_backend.dto.users.UpdatePasswordResponse;
import com.example.procon34_CYBER_WARS_backend.repository.UsersRepository;
import com.example.procon34_CYBER_WARS_backend.utility.PasswordEncoder;
import com.example.procon34_CYBER_WARS_backend.utility.StringFormatter;
import com.example.procon34_CYBER_WARS_backend.utility.UserGetterByName;
import com.example.procon34_CYBER_WARS_backend.utility.UserIdGetter;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UsersService {

    private final UsersRepository usersRepository;
    private final UserGetterByName userGetterByName;
    private final UserIdGetter userIdGetter;
    private final StringFormatter stringFormatter;
    private final PasswordEncoder passwordEncoder;

    // ユーザー登録
    public RegisterUserResponse registerUser(final RegisterUserRequest registerUserRequest) {
        final String formattedName = stringFormatter.formatString(registerUserRequest.getName());

        // ユーザー名が空の場合
        if (formattedName.isEmpty()) {
            return new RegisterUserResponse(false);
        }

        // ユーザーが存在する場合
        if (userGetterByName.getUserByName(formattedName) != null) {
            return new RegisterUserResponse(false);
        }

        final String formattedPassword = stringFormatter.formatString(registerUserRequest.getPassword());

        // ユーザーパスワードが空の場合
        if (formattedPassword.isEmpty()) {
            return new RegisterUserResponse(false);
        }

        usersRepository.registerUser(formattedName, passwordEncoder.hashPassword(formattedPassword));

        return new RegisterUserResponse(true);
    }

    // ユーザー名更新
    public UpdateNameResponse updateName(final UpdateNameRequest updateNameRequest,
            final HttpServletRequest httpServletRequest) {
        final String formattedName = stringFormatter.formatString(updateNameRequest.getName());

        // ユーザー名が空の場合
        if (formattedName.isEmpty()) {
            return new UpdateNameResponse(false);
        }

        // ユーザーが存在する場合
        if (userGetterByName.getUserByName(formattedName) != null) {
            return new UpdateNameResponse(false);
        }

        usersRepository.updateName(userIdGetter.getUserId(httpServletRequest), formattedName);

        return new UpdateNameResponse(true);
    }

    // ユーザーパスワード更新
    public UpdatePasswordResponse updatePassword(final UpdatePasswordRequest updatePasswordRequest,
            final HttpServletRequest httpServletRequest) {
        final String formattedPassword = stringFormatter.formatString(updatePasswordRequest.getPassword());

        // ユーザーパスワードが空の場合
        if (formattedPassword.isEmpty()) {
            return new UpdatePasswordResponse(false);
        }

        usersRepository.updatePassword(
                userIdGetter.getUserId(httpServletRequest),
                passwordEncoder.hashPassword(formattedPassword));

        return new UpdatePasswordResponse(true);
    }

}
