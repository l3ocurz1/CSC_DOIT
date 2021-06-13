import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompetenzeInfoComponent } from './competenze-info.component';

describe('CompetenzeInfoComponent', () => {
  let component: CompetenzeInfoComponent;
  let fixture: ComponentFixture<CompetenzeInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CompetenzeInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CompetenzeInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
