import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListExpertsComponent } from './list-experts.component';

describe('ListExpertsComponent', () => {
  let component: ListExpertsComponent;
  let fixture: ComponentFixture<ListExpertsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListExpertsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListExpertsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
