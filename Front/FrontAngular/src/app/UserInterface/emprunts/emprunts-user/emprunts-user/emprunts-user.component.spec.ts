import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpruntsUserComponent } from './emprunts-user.component';

describe('EmpruntsUserComponent', () => {
  let component: EmpruntsUserComponent;
  let fixture: ComponentFixture<EmpruntsUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EmpruntsUserComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmpruntsUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
