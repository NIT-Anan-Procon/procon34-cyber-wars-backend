package com.example.procon34_CYBER_WARS_backend.dto.Users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SearchUserByNameRequest {

    @NotBlank
    @Size(max = 20)
    private String name;

}
