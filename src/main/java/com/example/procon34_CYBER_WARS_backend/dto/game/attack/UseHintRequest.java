package com.example.procon34_CYBER_WARS_backend.dto.game.attack;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UseHintRequest {

    @NotBlank
    private int vulnerabilityId;

}
