import { TestBed } from '@angular/core/testing';

import { TestingAPIService } from './testing-api.service';

describe('TestingAPIService', () => {
  let service: TestingAPIService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TestingAPIService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
