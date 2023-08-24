package com.example.procon34_CYBER_WARS_backend.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Users {

    private int user_id;

    private final String name;

    private String password;

}
