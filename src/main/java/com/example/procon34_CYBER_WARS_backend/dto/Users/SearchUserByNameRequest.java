package com.example.procon34_CYBER_WARS_backend.dto.Users;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Component
public class SearchUserByNameRequest {

    @NotBlank
    @Size(max = 20)
    private String name;

}
