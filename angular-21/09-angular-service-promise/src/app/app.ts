import { Component, inject, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Items } from './services/items';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
  ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('angular-starter');

  message:string='';
  constructor(private items: Items) {
  }

  onLoadItems() {
    console.log('App:onLoadItems');
    this.items.load()
      .then(() => { 
        this.message = 'onLoadItems:then';
        console.log('App:onLoadItems:then') })
      .catch(() => { 
        this.message = 'onLoadItems:catch';
        console.log('onLoadItems:catch') 
      })
      .finally(() => { console.log('App:onLoadItems:finally') });
  }

}