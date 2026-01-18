package com.ganatan.starter.continents;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ContinentRequestDTO(
  @NotBlank(message = "name is required")
  @Size(max = 50, message = "name must not exceed 50 characters")
  String name
) {}

