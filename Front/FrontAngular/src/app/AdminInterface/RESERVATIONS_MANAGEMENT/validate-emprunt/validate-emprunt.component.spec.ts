import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ValidateEmpruntComponent } from './validate-emprunt.component';

describe('ValidateEmpruntComponent', () => {
  let component: ValidateEmpruntComponent;
  let fixture: ComponentFixture<ValidateEmpruntComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ValidateEmpruntComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ValidateEmpruntComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
