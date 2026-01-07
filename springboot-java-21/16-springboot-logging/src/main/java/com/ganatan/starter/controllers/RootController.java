package com.ganatan.starter.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

	private static final Logger log = LoggerFactory.getLogger(RootController.class);

	@GetMapping("/")
	public String root() {
		log.info("GET / - Spring Boot Starter is running!");
		return "Spring Boot Starter is running!";
	}
}