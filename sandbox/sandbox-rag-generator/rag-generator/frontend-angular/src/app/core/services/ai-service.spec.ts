import { TestBed } from '@angular/core/testing';
import { AiService, ContentGenerationResponse } from './ai-service';
import { provideHttpClient } from '@angular/common/http';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { environment } from '../../../environments/environment';
import { HttpErrorResponse } from '@angular/common/http';

describe('AiService', () => {
  let service: AiService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        provideHttpClient(),
        provideHttpClientTesting(),
        AiService,
      ],
    });
    service = TestBed.inject(AiService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    // Arrange
    // Act
    const instance = service;
    // Assert
    expect(instance).toBeTruthy();
  });

  it('should return mock data when useMock = true', (done) => {
    // Arrange
    environment.useMock = true;
    // Act
    service.generateContent('chatgpt', 'Test Question', 'medium', 'neutral', 'rag')
      .subscribe((response: ContentGenerationResponse) => {
        // Assert
        expect(response.success).toBeTrue();
        expect(response.data).toContain('Test');
        done();
      });
  });

  it('should send HTTP POST request when useMock = false', (done) => {
    // Arrange
    environment.useMock = false;
    const dummyResponse: ContentGenerationResponse = { success: true, data: 'Hello from backend' };
    // Act
    service.generateContent('chatgpt', 'Test Question', 'medium', 'neutral', 'rag')
      .subscribe((response) => {
        // Assert
        expect(response.success).toBeTrue();
        expect(response.data).toBe('Hello from backend');
        done();
      });
    const req = httpMock.expectOne(`${environment.backend}/llm/rag/chatgpt`);
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual({ name: 'Test Question', length: 'medium', style: 'neutral' });
    req.flush(dummyResponse);
  });

  it('should handle HTTP error correctly', (done) => {
    // Arrange
    environment.useMock = false;
    // Act
    service.generateContent('chatgpt', 'Test Question', 'medium', 'neutral', 'rag')
      .subscribe((response) => {
        // Assert
        expect(response.success).toBeFalse();
        expect(response.data).toBe('');
        expect(response.error).toContain('Erreur 500');
        done();
      });
    const req = httpMock.expectOne(`${environment.backend}/llm/rag/chatgpt`);
    req.flush('Internal error', { status: 500, statusText: 'Internal Server Error' });
  });

  it('should return proper message for network error (status 0)', () => {
    // Arrange
    const error = new HttpErrorResponse({ status: 0, statusText: 'Unknown Error' });
    const getErrorMessage = (service as unknown as { getErrorMessage: (e: HttpErrorResponse) => string }).getErrorMessage;
    // Act
    const result = getErrorMessage(error);
    // Assert
    expect(result).toBe('Serveur inaccessible. Vérifiez votre connexion.');
  });

  it('should return formatted message for generic error', () => {
    // Arrange
    const error = new HttpErrorResponse({ status: 404, statusText: 'Not Found', error: 'Missing' });
    const getErrorMessage = (service as unknown as { getErrorMessage: (e: HttpErrorResponse) => string }).getErrorMessage;
    // Act
    const result = getErrorMessage(error);
    // Assert
    expect(result).toBe(`Erreur 404: ${error.message}`);
  });
});
