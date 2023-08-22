package com.example.procon34_CYBER_WARS_backend.dto.Rooms;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateRoomRequest {

    private int userId;

    @NotNull
    private boolean difficult;

    private short inviteId;

}
