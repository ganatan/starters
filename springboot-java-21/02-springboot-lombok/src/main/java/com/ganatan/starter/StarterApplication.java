package com.ganatan.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ganatan.starter.modules.person.*;

@SpringBootApplication
public class StarterApplication {

  public static void main(String[] args) {
    Person person = new Person();
    System.out.println("00000000001");
    person.setName("Aliens");
    System.out.println("00000000001"+ person.getId());
    System.out.println("00000000001"+ person.getName());
    SpringApplication.run(StarterApplication.class, args);
  }

}
