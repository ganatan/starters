import { TestBed, ComponentFixture } from '@angular/core/testing';
import { App } from './app';
import { ActivatedRoute } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { AiService } from './core/services/ai-service';
import { ContentGenerationResponse } from './core/services/ai-service';

describe('App Component', () => {
  let fixture: ComponentFixture<App>;
  let app: App;
  let aiServiceMock: jasmine.SpyObj<AiService>;

  beforeEach(async () => {
    aiServiceMock = jasmine.createSpyObj('AiService', ['generateContent']);
    await TestBed.configureTestingModule({
      imports: [App],
      providers: [
        provideHttpClient(),
        { provide: ActivatedRoute, useValue: {} },
        { provide: AiService, useValue: aiServiceMock },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(App);
    app = fixture.componentInstance;
  });

  it('should create the component', () => {
    // Arrange
    // Act
    const result = app;
    // Assert
    expect(result).toBeTruthy();
  });

  it('should reset content when resetContent() is called', () => {
    // Arrange
    app.content = 'Some content';
    app.error = 'Some error';
    app.duration = 42;
    app.progress = 80;
    app.loading = true;
    // Act
    app.resetContent();
    // Assert
    expect(app.content).toBe('');
    expect(app.error).toBeNull();
    expect(app.duration).toBe(0);
    expect(app.progress).toBe(0);
    expect(app.loading).toBe(false);
  });

  it('should toggle dark mode class on document', () => {
    // Arrange
    document.body.classList.remove('dark-mode');
    document.documentElement.classList.remove('dark-mode');
    // Act
    app.toggleTheme();
    // Assert
    expect(document.body.classList.contains('dark-mode')).toBeTrue();
    expect(document.documentElement.classList.contains('dark-mode')).toBeTrue();
  });

  it('should handle AiService.generateContent error', (done) => {
    // Arrange
    const mockResponse: ContentGenerationResponse = {
      success: false,
      data: '',
      error: 'API Error',
    };
    aiServiceMock.generateContent.and.returnValue(of(mockResponse));
    // Act
    app.loadContent();
    // Assert
    setTimeout(() => {
      expect(app.loading).toBeFalse();
      expect(app.error).toBe('API Error');
      expect(app.content).toBe('');
      done();
    }, 150);
  });

  it('should update style and reset content when onStyleChange is called', () => {
    // Arrange
    app.content = 'old content';
    // Act
    app.onStyleChange('cinematic');
    // Assert
    expect(app.style).toBe('cinematic');
    expect(app.content).toBe('');
  });

  it('should update length and reset content when onLengthChange is called', () => {
    // Arrange
    app.content = 'old content';
    // Act
    app.onLengthChange('long');
    // Assert
    expect(app.length).toBe('long');
    expect(app.content).toBe('');
  });

  it('should update model and reset content when onModelChange is called', () => {
    // Arrange
    app.content = 'old content';
    // Act
    app.onModelChange('claude');
    // Assert
    expect(app.model).toBe('claude');
    expect(app.content).toBe('');
  });

  it('should update mode and reset content when onModeChange is called', () => {
    // Arrange
    app.content = 'old content';
    // Act
    app.onModeChange('direct');
    // Assert
    expect(app.mode).toBe('direct');
    expect(app.content).toBe('');
  });

  it('should gradually increase progress with startProgress()', (done) => {
    // Arrange
    // Act
    const interval = app['startProgress']();
    // Assert
    setTimeout(() => {
      clearInterval(interval);
      expect(app.progress).toBeGreaterThan(0);
      expect(app.progress).toBeLessThanOrEqual(95);
      done();
    }, 500);
  });
});
