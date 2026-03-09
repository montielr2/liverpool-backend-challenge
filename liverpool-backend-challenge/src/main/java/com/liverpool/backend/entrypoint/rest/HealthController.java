package com.liverpool.backend.entrypoint.rest;

import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Health check endpoint for the API.
 */
@RestController
public class HealthController {
  @GetMapping("/health")
  public ResponseEntity<Map<String, Object>> health() {
    return ResponseEntity.ok(
        Map.of(
            "status", "UP",
            "service", "liverpool-backend-challenge",
            "timestamp", LocalDateTime.now().toString()));
  }
}
