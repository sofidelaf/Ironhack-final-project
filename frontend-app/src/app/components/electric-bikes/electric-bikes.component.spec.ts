import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ElectricBikesComponent } from './electric-bikes.component';

describe('ElectricBikesComponent', () => {
  let component: ElectricBikesComponent;
  let fixture: ComponentFixture<ElectricBikesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ElectricBikesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ElectricBikesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
