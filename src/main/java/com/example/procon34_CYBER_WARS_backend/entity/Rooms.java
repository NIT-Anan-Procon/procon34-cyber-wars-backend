package com.example.procon34_CYBER_WARS_backend.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Rooms {

    private int roomId;

    private short inviteId;

    private int challengeId;

    private Timestamp startedAt;

    private byte status;

}
