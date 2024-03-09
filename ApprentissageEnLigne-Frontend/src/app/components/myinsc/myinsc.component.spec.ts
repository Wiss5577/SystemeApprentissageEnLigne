import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyinscComponent } from './myinsc.component';

describe('MyinscComponent', () => {
  let component: MyinscComponent;
  let fixture: ComponentFixture<MyinscComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MyinscComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MyinscComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
