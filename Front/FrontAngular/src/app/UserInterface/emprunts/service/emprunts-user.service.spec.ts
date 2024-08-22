import { TestBed } from '@angular/core/testing';

import { EmpruntsUserService } from './emprunts-user.service';

describe('EmpruntsUserService', () => {
  let service: EmpruntsUserService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmpruntsUserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
