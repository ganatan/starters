package com.ganatan.starter.api.root;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("unused")
public class RootController {

  public record ApiInfo(String application, String status, String java) {}

  @GetMapping("/")
  public Map<String, Object> rootWithMapOf() {
    return Map.of(
      "application", "springboot-starter",
      "status", "running",
      "java", System.getProperty("java.version")
    );
  }

  @GetMapping("/info")
  public ApiInfo rootWithRecord() {
    return new ApiInfo(
      "springboot-starter",
      "running",
      System.getProperty("java.version")
    );
  }

  @GetMapping("/status")
  public Map<String, Object> rootWithHashMap() {
    Map<String, Object> response = new HashMap<>();
    response.put("application", "springboot-starter");
    response.put("status", "running");
    response.put("java", System.getProperty("java.version"));
    return response;
  }

}