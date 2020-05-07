import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ErrorpagecomponentComponent } from './errorpagecomponent.component';

describe('ErrorpagecomponentComponent', () => {
  let component: ErrorpagecomponentComponent;
  let fixture: ComponentFixture<ErrorpagecomponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ErrorpagecomponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ErrorpagecomponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
