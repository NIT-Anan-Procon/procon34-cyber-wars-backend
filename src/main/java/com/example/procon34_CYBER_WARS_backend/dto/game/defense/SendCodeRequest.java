package com.example.procon34_CYBER_WARS_backend.dto.game.defense;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SendCodeRequest {

    @NotBlank
    private String code;

}
