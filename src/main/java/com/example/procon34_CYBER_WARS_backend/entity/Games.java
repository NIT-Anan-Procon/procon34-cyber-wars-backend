package com.example.procon34_CYBER_WARS_backend.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "games")
@Data
@Builder
public class Games {

    @EmbeddedId
    private final int room_id;

    @EmbeddedId
    private final int user_id;

    @EmbeddedId
    private final byte vulnerability_id;

    @EmbeddedId
    private final byte score_type;

}
