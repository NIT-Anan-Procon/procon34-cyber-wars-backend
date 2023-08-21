package com.example.procon34_CYBER_WARS_backend.dto.Users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserNameRequest {

    @NotBlank
    private int userId;

    @NotBlank
    @Size(max = 20)
    private String name;

}
