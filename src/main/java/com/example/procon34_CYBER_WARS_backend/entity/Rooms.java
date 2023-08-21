package com.example.procon34_CYBER_WARS_backend.entity;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Rooms {

    @NotBlank
    private int roomId;

    @NotBlank
    private short inviteId;

    @NotBlank
    private int challengeId;

    @NotBlank
    private Timestamp startedAt;

    @NotBlank
    private byte status;

}
