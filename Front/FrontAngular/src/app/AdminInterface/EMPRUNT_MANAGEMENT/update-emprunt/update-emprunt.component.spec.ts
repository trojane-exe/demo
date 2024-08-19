import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateEmpruntComponent } from './update-emprunt.component';

describe('UpdateEmpruntComponent', () => {
  let component: UpdateEmpruntComponent;
  let fixture: ComponentFixture<UpdateEmpruntComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpdateEmpruntComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateEmpruntComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
