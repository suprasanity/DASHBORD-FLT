import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WebserviceComponent } from './webservice.component';

describe('WebserviceComponent', () => {
  let component: WebserviceComponent;
  let fixture: ComponentFixture<WebserviceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WebserviceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WebserviceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
