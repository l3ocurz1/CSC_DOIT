import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SignupRedirectComponent } from './signup-redirect.component';

describe('SignupRedirectComponent', () => {
  let component: SignupRedirectComponent;
  let fixture: ComponentFixture<SignupRedirectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SignupRedirectComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SignupRedirectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
