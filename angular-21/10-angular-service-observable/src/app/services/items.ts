import { Injectable } from '@angular/core';
import { Observable, concat, of, throwError, timer } from 'rxjs';
import { delay, tap, mergeMap } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class Items {
  constructor() {
    console.log('Items:constructor');
  }

  load$(): Observable<boolean> {
    console.log('RxObservable:loadItems');

    return concat(
      of(true).pipe(
        delay(2000),
        tap(() => console.log('RxObservable:loadItems:next:true'))
      ),
      of(false).pipe(
        delay(2000),
        tap(() => console.log('RxObservable:loadItems:next:false'))
      ),
      timer(2000).pipe(
        tap(() => console.log('RxObservable:loadItems:error')),
        mergeMap(() => throwError(() => 'error'))
      )
    );
  }
}
