package com.example.procon34_CYBER_WARS_backend.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Vulnerabilities {

    private byte vulnerability_id;

    private String choice;

    private String hint;

    private String flag;

    private boolean difficult;

    private int challenge_id;

}
