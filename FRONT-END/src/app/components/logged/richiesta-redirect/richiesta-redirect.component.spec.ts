import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RichiestaRedirectComponent } from './richiesta-redirect.component';

describe('RichiestaRedirectComponent', () => {
  let component: RichiestaRedirectComponent;
  let fixture: ComponentFixture<RichiestaRedirectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RichiestaRedirectComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RichiestaRedirectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
