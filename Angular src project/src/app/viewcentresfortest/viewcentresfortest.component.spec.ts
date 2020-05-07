import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewcentresfortestComponent } from './viewcentresfortest.component';

describe('ViewcentresfortestComponent', () => {
  let component: ViewcentresfortestComponent;
  let fixture: ComponentFixture<ViewcentresfortestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewcentresfortestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewcentresfortestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
