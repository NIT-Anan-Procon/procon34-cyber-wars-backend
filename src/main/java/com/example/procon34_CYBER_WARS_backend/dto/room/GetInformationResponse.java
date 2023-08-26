package com.example.procon34_CYBER_WARS_backend.dto.room;

import lombok.Data;

@Data
public class GetInformationResponse {

    private final boolean host;

    private final String opponentName;

}
