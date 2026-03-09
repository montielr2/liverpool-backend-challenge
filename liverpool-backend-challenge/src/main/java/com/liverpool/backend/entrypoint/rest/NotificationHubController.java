package com.liverpool.backend.entrypoint.rest;

import java.util.Map;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification-hub")
public class NotificationHubController {
  @PostMapping
  public ResponseEntity<Map<String, String>> notify(@RequestBody NotificationRequest request) {
    return ResponseEntity.accepted().body(Map.of("message", "Notification received"));
  }

  @Data
  public static class NotificationRequest {
    private String eventType;
    private String userId;
    private String message;
  }
}
