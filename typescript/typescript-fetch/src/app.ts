import { Observable } from "rxjs";

async function runFetchPromise() {
  const response = await fetch('https://jsonplaceholder.typicode.com/albums');
  const data = await response.json();

  let results: any[] = [];

  for (let i = 0; i < 4; i++) {
    results.push(data[i]);
  }
  let resultsMap = results.map((value) => {
    return { id: value.id, title: value.title }
  })
  console.log('runFetchPromise:' + JSON.stringify(resultsMap));
}

runFetchPromise();

function runFetchObservable(): Observable<{ id: number; title: string }[]> {
  let result = new Observable<{ id: number; title: string }[]>(subscriber => {
    fetch('https://jsonplaceholder.typicode.com/albums')
      .then(response => response.json())
      .then((data: any[]) => {
        let results: any[] = [];
        for (let i = 0; i < 4; i++) {
          results.push(data[i]);
        }
        let resultsMap = results.map((value) => {
          return { id: value.id, title: value.title };
        });
        subscriber.next(resultsMap);
        subscriber.complete();
      })
      .catch(error => {
        subscriber.error(error);
      });
  });
  return result;
}

runFetchObservable().subscribe({
  next: value => console.log('runFetchObservable:' + JSON.stringify(value)),
  error: err => console.log(err),
  complete: () => console.log('fini')
});