import { Observable } from 'rxjs';

function getItemsObservable(): Observable<boolean> {
  let result = new Observable<boolean>(subscriber => {
    const random = Math.random() < 0.5;
    setTimeout(() => {
      if (random) {
        console.log('getItemsObservable:00000000001:next');
        subscriber.next(true);
        subscriber.complete();
      } else {
        console.log('getItemsObservable:00000000001:error');
        subscriber.error(new Error('getItemsObservable failed'));
      }
    }, 1000);
  });
  return result;
}

function getItems(): void {
  console.log('getItems:00000000001:');
  getItemsObservable().subscribe({
    next: value => {
      console.log('getItems:00000000002:next:' + value);
    },
    error: error => {
      if (error instanceof Error) {
        console.log('getItems:00000000003:error:' + error.message);
      } else {
        console.log('getItems:00000000003:error:' + String(error));
      }
    },
    complete: () => {
      console.log('getItems:00000000004:complete');
    }
  });
}

setInterval(() => {
  console.log('00000000001:setInterval');
  getItems();
}, 4000);



// import { Observable } from "rxjs";

// function runObservable() {
//   console.log('00000000001:runObservable');
//   let result = new Observable(subscriber => {
//     subscriber.next(true);
//     subscriber.next(false);
//     console.log('00000000002:runObservable');
//     subscriber.complete();
//   })
//   return result;
// }

// runObservable().subscribe(
//   {
//     next: value => console.log('reçu:', value),
//     complete: () => console.log('fini')
//   }
// );
