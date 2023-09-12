package com.example.procon34_CYBER_WARS_backend.dto.user.credential;

import lombok.Data;

@Data
public class IsLoggedInResponse {

    private final boolean loggedIn;

    private final String name;

}
