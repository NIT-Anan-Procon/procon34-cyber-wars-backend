package com.example.procon34_CYBER_WARS_backend.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.example.procon34_CYBER_WARS_backend.dto.utility.HttpClientErrorHandlerResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HttpClientErrorHandler {

    private final UserManager userManager;

    // HTTPクライアントエラー処理
    public HttpClientErrorHandlerResponse handle(final BindingResult bindingResult,
            final HttpServletRequest httpServletRequest) {
        // バリデーションエラーがあった場合
        if (bindingResult != null && bindingResult.hasErrors()) {
            return new HttpClientErrorHandlerResponse(true,
                    ResponseEntity.badRequest().body(bindingResult.getAllErrors()));
        }
        // ユーザーログインをしていない場合
        if (httpServletRequest != null && !userManager.isLoggedIn(httpServletRequest)) {
            return new HttpClientErrorHandlerResponse(true,
                    ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication Error"));
        }
        return new HttpClientErrorHandlerResponse(false, null);
    }

}
