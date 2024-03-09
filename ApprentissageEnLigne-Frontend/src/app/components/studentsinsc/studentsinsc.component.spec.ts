import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentsinscComponent } from './studentsinsc.component';

describe('StudentsinscComponent', () => {
  let component: StudentsinscComponent;
  let fixture: ComponentFixture<StudentsinscComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StudentsinscComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentsinscComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
