package com.example.procon34_CYBER_WARS_backend.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.procon34_CYBER_WARS_backend.dto.game.defense.FetchCodeResponse;
import com.example.procon34_CYBER_WARS_backend.dto.game.defense.SendCodeRequest;
import com.example.procon34_CYBER_WARS_backend.dto.game.defense.SendCodeResponse;
import com.github.javaparser.ParseProblemException;
import com.github.javaparser.StaticJavaParser;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class GameDefenseService {

    // コード取得
    public FetchCodeResponse fetchCode(final HttpServletRequest httpServletRequest) {
        try {
            return new FetchCodeResponse(Files.readString(Paths.get("target1.php")));
        } catch (final IOException exception) {
            return new FetchCodeResponse(null);
        }
    }

    // コード送信
    public SendCodeResponse sendCode(final SendCodeRequest sendCodeRequest,
            final HttpServletRequest httpServletRequest) {
        try {
            StaticJavaParser.parse(sendCodeRequest.getCode());
            try {
                Files.writeString(Paths.get("target1.php"), sendCodeRequest.getCode());
                return new SendCodeResponse(true);
            } catch (final IOException exception) {
                return new SendCodeResponse(false);
            }
        } catch (final ParseProblemException exception) {
            return new SendCodeResponse(false);
        }
    }

}
