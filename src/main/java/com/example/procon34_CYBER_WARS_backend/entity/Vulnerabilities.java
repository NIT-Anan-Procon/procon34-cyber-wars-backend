package com.example.procon34_CYBER_WARS_backend.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Vulnerabilities {

    private final byte vulnerability_id;

    private final String choice;

    private final String hint;

    private final String flag;

    private final boolean difficult;

    private final int challenge_id;

}
