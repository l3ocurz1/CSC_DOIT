import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListProponentsComponent } from './list-proponents.component';

describe('ListProponentsComponent', () => {
  let component: ListProponentsComponent;
  let fixture: ComponentFixture<ListProponentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListProponentsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListProponentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
