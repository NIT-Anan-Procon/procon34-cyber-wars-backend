package com.example.procon34_CYBER_WARS_backend.dto.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateNameRequest {

    private int user_id;

    @NotBlank
    @Size(max = 20)
    private String name;

}
