import { Component, signal } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { ContinentsApi, Continent } from './services/continents-api';

@Component({
  selector: 'app-root',
  imports: [HttpClientModule, CommonModule],
  templateUrl: './app.html'
})
export class App {
  readonly continents = signal<Continent[]>([]);
  readonly error = signal('');

  constructor(private api: ContinentsApi) {}

  onLoadContinents() {
    this.error.set('');
    this.api.list$().subscribe({
      next: (data) => this.continents.set(data),
      error: () => this.error.set('Erreur API /continents')
    });
  }
}