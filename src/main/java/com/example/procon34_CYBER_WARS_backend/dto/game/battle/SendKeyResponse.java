package com.example.procon34_CYBER_WARS_backend.dto.game.battle;

import lombok.Data;

@Data
public class SendKeyResponse {

    private final boolean valid;

    private final boolean correct;

    private final int score;

}
