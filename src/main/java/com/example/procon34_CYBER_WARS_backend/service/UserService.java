package com.example.procon34_CYBER_WARS_backend.service;

import com.example.procon34_CYBER_WARS_backend.dto.user.*;
import com.example.procon34_CYBER_WARS_backend.repository.UsersRepository;
import com.example.procon34_CYBER_WARS_backend.utility.StringFormatter;
import com.example.procon34_CYBER_WARS_backend.utility.users.PasswordEncoder;
import com.example.procon34_CYBER_WARS_backend.utility.users.UserFetcherByName;
import com.example.procon34_CYBER_WARS_backend.utility.users.UserIdFetcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UsersRepository usersRepository;
    private final UserFetcherByName userFetcherByName;
    private final UserIdFetcher userIdFetcher;
    private final StringFormatter stringFormatter;
    private final PasswordEncoder passwordEncoder;

    // ユーザー登録
    public RegisterResponse register(final RegisterRequest registerRequest) {
        final String formattedName = stringFormatter.format(registerRequest.getName());

        // ユーザー名が空の場合
        if (formattedName.isEmpty()) {
            return new RegisterResponse(false);
        }

        // ユーザーが存在する場合
        if (userFetcherByName.fetchUserByName(formattedName) != null) {
            return new RegisterResponse(false);
        }

        final String formattedPassword = stringFormatter.format(registerRequest.getPassword());

        // ユーザーパスワードが空の場合
        if (formattedPassword.isEmpty()) {
            return new RegisterResponse(false);
        }

        usersRepository.register(formattedName, passwordEncoder.hash(formattedPassword));

        return new RegisterResponse(true);
    }

    // ユーザー名更新
    public UpdateNameResponse updateName(final UpdateNameRequest updateNameRequest,
            final HttpServletRequest httpServletRequest) {
        final String formattedName = stringFormatter.format(updateNameRequest.getName());

        // ユーザー名が空の場合
        if (formattedName.isEmpty()) {
            return new UpdateNameResponse(false);
        }

        // ユーザーが存在する場合
        if (userFetcherByName.fetchUserByName(formattedName) != null) {
            return new UpdateNameResponse(false);
        }

        usersRepository.updateName(userIdFetcher.fetchUserId(httpServletRequest), formattedName);

        return new UpdateNameResponse(true);
    }

    // ユーザーパスワード更新
    public UpdatePasswordResponse updatePassword(final UpdatePasswordRequest updatePasswordRequest,
            final HttpServletRequest httpServletRequest) {
        final String formattedPassword = stringFormatter.format(updatePasswordRequest.getPassword());

        // ユーザーパスワードが空の場合
        if (formattedPassword.isEmpty()) {
            return new UpdatePasswordResponse(false);
        }

        usersRepository.updatePassword(userIdFetcher.fetchUserId(httpServletRequest),
                passwordEncoder.hash(formattedPassword));

        return new UpdatePasswordResponse(true);
    }

}
