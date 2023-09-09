package com.example.procon34_CYBER_WARS_backend.dto.room;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateRequest {

    @NotNull
    private final boolean difficult;

}
