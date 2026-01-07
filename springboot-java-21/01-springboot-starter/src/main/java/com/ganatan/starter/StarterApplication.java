package com.ganatan.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StarterApplication {
  public static void main(String[] args) {
    String version = System.getProperty("java.version");
    System.out.println("Running with Java version: " + version);

    SpringApplication.run(StarterApplication.class, args);

  }

}
