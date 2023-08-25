package com.example.procon34_CYBER_WARS_backend.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "allocations")
@Data
@Builder
public class Allocations {

    @EmbeddedId
    private final int room_id;

    @EmbeddedId
    private final int user_id;

    private final boolean host;

}
