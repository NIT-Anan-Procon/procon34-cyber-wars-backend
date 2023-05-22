package com.example.procon34_CYBER_WARS_backend.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsersRegisterRequest implements Serializable {

    @NotBlank
    @Size(max = 20)
    private String name;

    @NotBlank
    @Size(max = 100)
    private String password;

}
