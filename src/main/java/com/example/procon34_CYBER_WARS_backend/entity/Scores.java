package com.example.procon34_CYBER_WARS_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "scores")
@Data
@Builder
public class Scores {

    @EmbeddedId
    private final byte score_type;

    @EmbeddedId
    private final boolean difficult;

    @Column(unique = true)
    private final byte score;

}
