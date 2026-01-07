package com.ganatan.starter.modules.person;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Person {
  private Long id;
  private String name;
}
