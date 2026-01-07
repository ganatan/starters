package com.ganatan.starter.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

	@GetMapping("/")
	public String root() {
		// System.out.println("00000000001:root jdk 21");
		// return "root jdk 21";

    String version = System.getProperty("java.version");
    System.out.println("Running with Java version: " + version);
    return "Java version: " + version;
	}
}
