package com.example.procon34_CYBER_WARS_backend.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdatePasswordRequest {

    @NotBlank
    @Size(max = 100)
    private final String password;

}
