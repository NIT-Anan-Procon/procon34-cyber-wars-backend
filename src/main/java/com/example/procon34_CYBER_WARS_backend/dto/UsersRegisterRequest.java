package com.example.procon34_CYBER_WARS_backend.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UsersRegisterRequest implements Serializable {

    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
