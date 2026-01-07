package com.ganatan.starter.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//class Title{
//  protected String name;
//  public Title(String name) {
//    this.name = name;
//    System.out.println("00000000001");
//  }
//}


@RestController
public class RootController {

	@GetMapping("/")
	public String root() {
//    Title title = new Title("Aliens");
//    System.out.println("00000000001:" + title.name);

    return "Spring Boot Starter is running!";

	}
}
