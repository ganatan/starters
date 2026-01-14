import { Component, signal } from '@angular/core';
import { Edit } from './components/edit/edit'

@Component({
  selector: 'app-root',
  imports: [Edit],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('angular-starter');
  appEditValue = 'Aliens';

  changeAppEdit(): void {
    this.appEditValue = `${this.appEditValue}Changed`;
  }

}