package com.example.procon34_CYBER_WARS_backend.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsersUpdateRequest {

    private Long userId;

    @Size(max = 20)
    private String name;

    @Size(max = 100)
    private String password;

}
