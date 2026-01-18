import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { JsonPipe } from '@angular/common';
import { CommonModule } from '@angular/common';

class Movie {
  public name: string = '';
  constructor() {
  }
}

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
    JsonPipe,
    CommonModule
  ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('angular-starter');

  items: string[] = [
    'Aliens',
    'Exodus',
    'Legend',
    'Gladiator'
  ]

  movies: Movie[] = [
    { name: 'Aliens' },
    { name: 'Exodus' },
    { name: 'Legend' },
    { name: 'Gladiator' },
  ]

  trackItem(index: number, item: string) {
    return item;
  }

}
