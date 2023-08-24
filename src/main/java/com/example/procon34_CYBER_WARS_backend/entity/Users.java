package com.example.procon34_CYBER_WARS_backend.entity;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Users {

    private int user_id;

    @Size(max = 20)
    private String name;

    @Size(max = 100)
    private String password;

}
