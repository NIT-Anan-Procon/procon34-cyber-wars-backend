package com.example.procon34_CYBER_WARS_backend.entity;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Rooms {

    private final int room_id;

    private final short invite_id;

    private final int challenge_id;

    private final Timestamp started_at;

    private final byte status;

}
