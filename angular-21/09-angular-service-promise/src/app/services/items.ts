import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class Items {
  constructor() {
    console.log('Items:constructor');
  }

  load() {
    console.log('Items:load');
    let result = new Promise((resolve, reject) => {
      setTimeout(() => {
        resolve(true);
        console.log('Items:resolve');
      }, 4000)
    })
    return result;
  }
  
}