package com.example.procon34_CYBER_WARS_backend.dto.users.Credentials;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginUserRequest {

    @NotBlank
    @Size(max = 20)
    private final String name;

    @NotBlank
    @Size(max = 100)
    private String password;

}
