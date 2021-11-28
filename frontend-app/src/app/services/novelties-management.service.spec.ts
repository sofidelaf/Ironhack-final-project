import { TestBed } from '@angular/core/testing';

import { NoveltiesManagementService } from './novelties-management.service';

describe('NoveltiesManagementService', () => {
  let service: NoveltiesManagementService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NoveltiesManagementService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
