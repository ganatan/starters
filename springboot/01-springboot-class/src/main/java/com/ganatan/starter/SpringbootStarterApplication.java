package com.ganatan.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//class Movie {
//  private String title;
//  private int releaseYear;
//  private double rating;
//  private int durationMinutes;
//
//  Movie(String title, int releaseYear, double rating, int durationMinutes) {
//  }
//
//  String getTitle() {
//    return this.title;
//  }
//
//  int getReleaseYear() {
//    return this.releaseYear;
//  }
//
//  double getRating() {
//    return this.rating;
//  }
//
//  int getDurationMinutes() {
//    return this.durationMinutes;
//  }
//
//  void setRating(double value) {
//    this.rating = value;
//  }
//
//  void setDurationMinutes(int value) {
//    this.durationMinutes = value;
//  }
//
//  public boolean isLongMovie() {
//    return false;
//  }
//}

class MovieService {
  void show() {
    System.out.println("show");
    throw new IllegalArgumentException("error");
  }
}

@SpringBootApplication
public class SpringbootStarterApplication {


  public static void main(String[] args) {

    MovieService movieService = new MovieService();
    movieService.show();
//    Movie m = new Movie("Gladiator", 2000, 8.5, 155);
//    System.out.println(m.isLongMovie());
//    m.setRating(9.1);
//    m.setDurationMinutes(140);
    System.out.println("00000000001");
    SpringApplication.run(SpringbootStarterApplication.class, args);
  }

}
