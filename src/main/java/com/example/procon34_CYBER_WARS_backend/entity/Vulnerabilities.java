package com.example.procon34_CYBER_WARS_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "vulnerabilities")
@Data
public class Vulnerabilities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final byte vulnerabilityId;

    private final String choice;

    private final String hint;

    private final String flag;

    private final boolean difficult;

    private final int challengeId;

}
