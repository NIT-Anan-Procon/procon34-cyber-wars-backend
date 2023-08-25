package com.example.procon34_CYBER_WARS_backend.dto.rooms;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetRoomInformationResponse {

    private final String hostName;

    private final String guestName;

}
