package com.ganatan.starter.root;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
public class RootController {


  @GetMapping("/")
  public Map<String, Object> root() {
    return Map.of(
      "name", "backend-springboot",
      "version", "1.0.0",
      "status", "ok",
      "timestamp", Instant.now().toString()
    );
  }

}
