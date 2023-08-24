package com.example.procon34_CYBER_WARS_backend.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Allocations {

    private final int room_id;

    private final int user_id;

}
