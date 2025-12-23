import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class Movie {

  getMovies(): string[] {
    return ['Aliens', 'Gladiator', 'Heat'];
  }
  
}
