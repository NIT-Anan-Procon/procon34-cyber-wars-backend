package com.example.procon34_CYBER_WARS_backend.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "allocations")
@Data
public class Allocations {

    @EmbeddedId
    private final int roomId;

    @EmbeddedId
    private final int userId;

    private final boolean host;

}
