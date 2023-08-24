package com.example.procon34_CYBER_WARS_backend.entity;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Rooms {

    private int room_id;

    private short invite_id;

    private int challenge_id;

    private Timestamp started_at;

    private byte status;

}
