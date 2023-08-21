package com.example.procon34_CYBER_WARS_backend.dto.Rooms;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateRoomRequest {

    @NotBlank
    private boolean isDifficult;

}
