import axios from "axios";
import { Observable } from "rxjs";

async function runAxiosPromise() {
  const response = await axios.get('https://jsonplaceholder.typicode.com/albums');
  const data = response.data;

  let results: any[] = [];
  for (let i = 0; i < 4; i++) {
    results.push(data[i]);
  }

  let resultsMap = results.map(value => {
    return { id: value.id, title: value.title };
  });

  console.log('runAxiosPromise:' + JSON.stringify(resultsMap));
}

runAxiosPromise();

function runAxiosObservable(): Observable<{ id: number; title: string }[]> {
  let result = new Observable<{ id: number; title: string }[]>(subscriber => {
    axios.get('https://jsonplaceholder.typicode.com/albums')
      .then(response => {
        const data = response.data;

        let results: any[] = [];
        for (let i = 0; i < 4; i++) {
          results.push(data[i]);
        }

        let resultsMap = results.map(value => {
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

runAxiosObservable().subscribe({
  next: value => console.log('runAxiosObservable:' + JSON.stringify(value)),
  error: err => console.log(err),
  complete: () => console.log('fini')
});
