package com.ganatan.starter.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

	@GetMapping("/info")
	public String root() {
		return "springboot-starter:info";
	}
}
