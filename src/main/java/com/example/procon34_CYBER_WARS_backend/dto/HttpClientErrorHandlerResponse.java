package com.example.procon34_CYBER_WARS_backend.dto;

import org.springframework.http.ResponseEntity;

import lombok.Data;

@Data
public class HttpClientErrorHandlerResponse {

    private final boolean Error;

    private final ResponseEntity<?> responseEntity;

}
