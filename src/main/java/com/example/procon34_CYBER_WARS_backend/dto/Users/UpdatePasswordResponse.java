package com.example.procon34_CYBER_WARS_backend.dto.users;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdatePasswordResponse {

    private final boolean success;

}
