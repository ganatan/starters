import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Items } from './services/items';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('angular-starter');

  message = '';
  private readonly destroy$ = new Subject<void>();

  constructor(private items: Items) {}

  onLoadItems() {
    console.log('App:onLoadItems');

    this.items.load$()
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (value) => {
          this.message = `onLoadItems:next:${value}`;
          console.log('App:onLoadItems:next', value);
        },
        error: () => {
          this.message = 'onLoadItems:error';
          console.log('App:onLoadItems:error');
        },
        complete: () => {
          this.message = 'onLoadItems:complete';
          console.log('App:onLoadItems:complete');
        }
      });
  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
