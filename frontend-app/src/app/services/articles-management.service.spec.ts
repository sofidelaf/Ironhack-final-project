import { TestBed } from '@angular/core/testing';

import { ArticlesManagementService } from './articles-management.service';

describe('ArticlesManagementService', () => {
  let service: ArticlesManagementService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ArticlesManagementService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
