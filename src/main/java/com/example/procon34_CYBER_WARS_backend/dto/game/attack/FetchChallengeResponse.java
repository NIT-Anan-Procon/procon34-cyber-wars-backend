package com.example.procon34_CYBER_WARS_backend.dto.game.attack;

import java.util.List;

import com.example.procon34_CYBER_WARS_backend.pojo.Vulnerability;

import lombok.Data;

@Data
public class FetchChallengeResponse {

    private final String path;

    private final List<Vulnerability> vulnerabilities;

}
