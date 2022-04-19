import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostPromptComponent } from './post-prompt.component';

describe('PostPromptComponent', () => {
  let component: PostPromptComponent;
  let fixture: ComponentFixture<PostPromptComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PostPromptComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PostPromptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
