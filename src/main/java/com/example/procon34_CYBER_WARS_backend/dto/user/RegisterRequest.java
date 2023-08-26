package com.example.procon34_CYBER_WARS_backend.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank
    @Size(max = 20)
    private String name;

    @NotBlank
    @Size(max = 100)
    private String password;

}