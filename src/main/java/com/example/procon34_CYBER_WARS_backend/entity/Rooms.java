package com.example.procon34_CYBER_WARS_backend.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "rooms")
@Data
@Builder
public class Rooms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final int room_id;

    private final short invite_id;

    private final int challenge_id;

    private final Timestamp started_at;

    private final byte status;

}
