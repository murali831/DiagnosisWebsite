import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchcentreComponent } from './searchcentre.component';

describe('SearchcentreComponent', () => {
  let component: SearchcentreComponent;
  let fixture: ComponentFixture<SearchcentreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchcentreComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchcentreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
