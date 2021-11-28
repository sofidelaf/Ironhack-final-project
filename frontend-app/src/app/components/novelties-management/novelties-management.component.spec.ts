import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NoveltiesManagementComponent } from './novelties-management.component';

describe('NoveltiesManagementComponent', () => {
  let component: NoveltiesManagementComponent;
  let fixture: ComponentFixture<NoveltiesManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NoveltiesManagementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NoveltiesManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
