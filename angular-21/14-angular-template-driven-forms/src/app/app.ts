import { Component, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  imports: [FormsModule],
  templateUrl: './app.html'
})
export class App {

  name = 'Alien';

  readonly nameS = signal('Alien');

  change() {
    this.name = 'Exodus';
    this.nameS.set('Exodus');
  }
}
