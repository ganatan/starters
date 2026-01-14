import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { HighlightBinding } from './directives/highlight-binding';
import { HighlightRenderer } from './directives/highlight-renderer';
@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
    HighlightBinding,
    HighlightRenderer,
  ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('angular-starter');

  constructor() {
  }

}
