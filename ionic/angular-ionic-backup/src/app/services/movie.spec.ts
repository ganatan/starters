import { TestBed } from '@angular/core/testing';

import { Movie } from './movie';

describe('Movie', () => {
  let service: Movie;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Movie);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
