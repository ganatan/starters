import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';

export type ContinentDTO = { id: number; name: string };
export type Continent = { id: number; label: string };

@Injectable({ providedIn: 'root' })
export class ContinentsApi {
  private readonly baseUrl = 'http://localhost:3000/continents';

  constructor(private http: HttpClient) {}

  list$(): Observable<Continent[]> {
    return this.http.get<ContinentDTO[]>(this.baseUrl).pipe(
      map(dtos => dtos.map(dto => ({ id: dto.id, label: dto.name })))
    );
  }
}