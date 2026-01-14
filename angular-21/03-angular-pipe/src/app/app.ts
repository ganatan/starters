import { Component, signal } from '@angular/core';

import { BudgetPipe } from './pipes/budget-pipe';
import { CurrencyPipe } from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [
    BudgetPipe,
    CurrencyPipe
  ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('angular-starter');
  budgetMovie: number = 714844358;
  nameMovie: string = 'Dune: Part Two'

}