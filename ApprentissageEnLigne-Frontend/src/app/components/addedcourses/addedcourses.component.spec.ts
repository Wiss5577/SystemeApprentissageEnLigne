import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddedcoursesComponent } from './addedcourses.component';

describe('AddedcoursesComponent', () => {
  let component: AddedcoursesComponent;
  let fixture: ComponentFixture<AddedcoursesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddedcoursesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddedcoursesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
