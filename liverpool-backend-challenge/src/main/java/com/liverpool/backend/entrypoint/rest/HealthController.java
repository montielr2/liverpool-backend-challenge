package com.liverpool.backend.entrypoint.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.Map;

public class HealthController {
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        return ResponseEntity.ok(Map.of(
                "status", "UP",
                "service", "liverpool-backend-challenge",
                "timestamp", LocalDateTime.now().toString()
        ));
    }
}
