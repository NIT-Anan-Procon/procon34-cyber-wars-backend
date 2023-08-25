package com.example.procon34_CYBER_WARS_backend.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "rooms")
@Data
public class Rooms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final int roomId;

    private final short inviteId;

    private final int challengeId;

    private final Timestamp startedAt;

    private final boolean started;

}
