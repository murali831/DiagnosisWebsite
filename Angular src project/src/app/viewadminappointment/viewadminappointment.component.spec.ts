import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewadminappointmentComponent } from './viewadminappointment.component';

describe('ViewadminappointmentComponent', () => {
  let component: ViewadminappointmentComponent;
  let fixture: ComponentFixture<ViewadminappointmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewadminappointmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewadminappointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
