package com.example.procon34_CYBER_WARS_backend.dto.room;

import lombok.Data;

@Data
public class FetchInformationResponse {

    private final String opponentName;

    private final boolean host;

    private final boolean started;

}