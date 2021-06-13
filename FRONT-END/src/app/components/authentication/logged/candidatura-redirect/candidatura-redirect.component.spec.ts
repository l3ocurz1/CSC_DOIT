import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CandidaturaRedirectComponent } from './candidatura-redirect.component';

describe('CandidaturaRedirectComponent', () => {
  let component: CandidaturaRedirectComponent;
  let fixture: ComponentFixture<CandidaturaRedirectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CandidaturaRedirectComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CandidaturaRedirectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
