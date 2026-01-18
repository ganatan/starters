import { TestBed } from '@angular/core/testing';

import { ContinentsApi } from './continents-api';

describe('ContinentsApi', () => {
  let service: ContinentsApi;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ContinentsApi);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
