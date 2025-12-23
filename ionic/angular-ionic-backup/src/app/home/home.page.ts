import { Component } from '@angular/core';

import { Movie } from '../services/movie';
import { CommonModule } from '@angular/common';
import {
  IonHeader,
  IonToolbar,
  IonTitle,
  IonContent,
  IonButton,
  IonList,
  IonItem
} from '@ionic/angular/standalone';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
  imports: [
    CommonModule,
    IonHeader, 
    IonToolbar, 
    IonTitle, 
    IonContent,
    IonButton,
    IonList,
    IonItem
  ],
})
export class HomePage {
  movies: string[] = [];

  constructor(private movie: Movie) {

  }

  load() {
    this.movies = this.movie.getMovies();
  }
}
