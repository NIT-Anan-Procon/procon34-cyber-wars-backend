package com.example.procon34_CYBER_WARS_backend.dto.rooms;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateRoomRequest {

    @NotNull
    private boolean difficult;

}
