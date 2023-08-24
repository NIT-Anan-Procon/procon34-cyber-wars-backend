package com.example.procon34_CYBER_WARS_backend.dto.users.Credentials;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckUserLoginResponse {

    private final boolean logged_in;

}
