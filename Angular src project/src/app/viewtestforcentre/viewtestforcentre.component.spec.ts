import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewtestforcentreComponent } from './viewtestforcentre.component';

describe('ViewtestforcentreComponent', () => {
  let component: ViewtestforcentreComponent;
  let fixture: ComponentFixture<ViewtestforcentreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewtestforcentreComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewtestforcentreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
