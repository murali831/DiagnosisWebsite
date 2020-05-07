import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DiagnosiscentreComponent } from './diagnosiscentre.component';

describe('DiagnosiscentreComponent', () => {
  let component: DiagnosiscentreComponent;
  let fixture: ComponentFixture<DiagnosiscentreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DiagnosiscentreComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DiagnosiscentreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
