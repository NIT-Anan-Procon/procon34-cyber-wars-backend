package com.example.procon34_CYBER_WARS_backend.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "games")
@Data
public class Games {

    @EmbeddedId
    private final int roomId;

    @EmbeddedId
    private final int userId;

    @EmbeddedId
    private final byte vulnerabilityId;

    @EmbeddedId
    private final byte score_type;

}
