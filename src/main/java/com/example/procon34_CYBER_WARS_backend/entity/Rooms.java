package com.example.procon34_CYBER_WARS_backend.entity;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Rooms {

    @NotBlank
    private int room_id;

    @NotBlank
    private short invite_id;

    @NotBlank
    private int challenge_id;

    @NotBlank
    private Timestamp started_at;

    @NotBlank
    private byte status;

}
