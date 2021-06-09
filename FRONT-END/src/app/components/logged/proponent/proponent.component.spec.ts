import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProponentComponent } from './proponent.component';

describe('ProponentComponent', () => {
  let component: ProponentComponent;
  let fixture: ComponentFixture<ProponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProponentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
