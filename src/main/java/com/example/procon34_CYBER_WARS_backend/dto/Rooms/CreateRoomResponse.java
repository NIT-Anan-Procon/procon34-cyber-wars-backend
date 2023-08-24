package com.example.procon34_CYBER_WARS_backend.dto.rooms;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateRoomResponse {

    private final short invite_id;

}
