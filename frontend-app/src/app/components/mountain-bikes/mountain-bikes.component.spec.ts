import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MountainBikesComponent } from './mountain-bikes.component';

describe('MountainBikesComponent', () => {
  let component: MountainBikesComponent;
  let fixture: ComponentFixture<MountainBikesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MountainBikesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MountainBikesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
