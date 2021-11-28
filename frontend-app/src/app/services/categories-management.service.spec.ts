import { TestBed } from '@angular/core/testing';

import { CategoriesManagementService } from './categories-management.service';

describe('CategoriesManagementService', () => {
  let service: CategoriesManagementService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CategoriesManagementService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
