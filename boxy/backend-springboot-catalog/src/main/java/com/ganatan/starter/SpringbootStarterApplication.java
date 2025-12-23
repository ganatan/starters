package com.ganatan.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

interface MovieInterface {
  void delete();
}

class MovieModel implements MovieInterface {

  public String name = "default";

  public void show() {
    System.out.println("MovieModel:show " + name);
  }

  @Override
  public void delete() {
    System.out.println("MovieModel:delete");
  }

}

class Movie extends MovieModel {

  @Override
  public void show() {
    this.name = "Movie";
    super.show();
    System.out.println("Movie:show");
  }

  @Override
  public void delete() {
    super.delete();
    System.out.println("Movie:delete");
  }

}

@SpringBootApplication
public class SpringbootStarterApplication {

  public static void main(String[] args) {

    MovieInterface movie = new Movie();
    movie.delete();

    Movie realMovie = new Movie();
    realMovie.show();

    SpringApplication.run(SpringbootStarterApplication.class, args);
  }

}
