import { TestBed } from '@angular/core/testing';

import { DiscountsManagementService } from './discounts-management.service';

describe('DiscountsManagementService', () => {
  let service: DiscountsManagementService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DiscountsManagementService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
