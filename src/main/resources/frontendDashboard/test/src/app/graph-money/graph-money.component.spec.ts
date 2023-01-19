import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GraphMoneyComponent } from './graph-money.component';

describe('GraphMoneyComponent', () => {
  let component: GraphMoneyComponent;
  let fixture: ComponentFixture<GraphMoneyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GraphMoneyComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GraphMoneyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
