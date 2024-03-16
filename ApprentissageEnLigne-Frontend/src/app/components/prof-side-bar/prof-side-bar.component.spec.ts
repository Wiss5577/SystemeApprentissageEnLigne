import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfSideBarComponent } from './prof-side-bar.component';

describe('ProfSideBarComponent', () => {
  let component: ProfSideBarComponent;
  let fixture: ComponentFixture<ProfSideBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfSideBarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfSideBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
