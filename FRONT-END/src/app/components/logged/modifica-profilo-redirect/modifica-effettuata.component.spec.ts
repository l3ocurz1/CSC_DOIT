import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModificaEffettuataComponent } from './modifica-effettuata.component';

describe('ModificaEffettuataComponent', () => {
  let component: ModificaEffettuataComponent;
  let fixture: ComponentFixture<ModificaEffettuataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModificaEffettuataComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModificaEffettuataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
