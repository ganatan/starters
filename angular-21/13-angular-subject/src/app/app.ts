import { Component, OnInit, OnDestroy } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { BehaviorSubject, Subscription } from 'rxjs';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CommonModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit, OnDestroy {

  private readonly moviesSubject = new BehaviorSubject<string[]>([
    'Aliens',
    'Gladiator',
    'Exodus',
    'Legend',
  ]);

  movies: string[] = [];

  private subscription!: Subscription;

  ngOnInit() {
    this.subscription = this.moviesSubject.subscribe(value => {
      this.movies = value;
    });
  }

  addMovie(name: string) {
    const value = name.trim();
    if (!value) return;
    this.moviesSubject.next([...this.movies, value]);
  }

  removeMovie(name: string) {
    this.moviesSubject.next(this.movies.filter(m => m !== name));
  }

  resetMovies() {
    this.moviesSubject.next(['Aliens', 'Gladiator', 'Exodus', 'Legend']);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
