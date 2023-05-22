package com.example.procon34_CYBER_WARS_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsersRegisterRequest {

    @NotBlank
    @Size(max = 20)
    public String name;

    @NotBlank
    @Size(max = 100)
    public String password;

}
