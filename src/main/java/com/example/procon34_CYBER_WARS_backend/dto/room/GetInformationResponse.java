package com.example.procon34_CYBER_WARS_backend.dto.room;

import lombok.Data;

@Data
public class GetInformationResponse {

    private final String opponentName;

    private final boolean isHost;

    private final boolean started;

}
