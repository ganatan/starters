import { TestBed } from '@angular/core/testing';

import { Items } from './items';

describe('Items', () => {
  let service: Items;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Items);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
