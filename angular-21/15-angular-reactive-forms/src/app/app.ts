import { Component, signal } from '@angular/core';
import { ReactiveFormsModule, FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-root',
  imports: [ReactiveFormsModule],
  templateUrl: './app.html'
})
export class App {

  name = new FormControl('Alien');

  readonly nameS = signal('Alien');
  readonly formS = new FormGroup({
    name: new FormControl('Alien')
  });

  change() {
    this.name.setValue('Exodus');
    this.formS.get('name')!.setValue('Exodus');
    this.nameS.set('Exodus');
  }
}
