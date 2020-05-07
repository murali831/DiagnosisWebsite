import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddtestforcentreComponent } from './addtestforcentre.component';

describe('AddtestforcentreComponent', () => {
  let component: AddtestforcentreComponent;
  let fixture: ComponentFixture<AddtestforcentreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddtestforcentreComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddtestforcentreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
