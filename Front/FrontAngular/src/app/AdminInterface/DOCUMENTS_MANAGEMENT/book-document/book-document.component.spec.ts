import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookDocumentComponent } from './book-document.component';

describe('BookDocumentComponent', () => {
  let component: BookDocumentComponent;
  let fixture: ComponentFixture<BookDocumentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BookDocumentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BookDocumentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
