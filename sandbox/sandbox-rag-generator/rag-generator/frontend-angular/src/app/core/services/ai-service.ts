import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, delay } from 'rxjs/operators';
import { environment } from '../../../environments/environment';
import { reply as mockReply } from './ai.mock';

export interface ContentGenerationResponse {
  success: boolean;
  data: string;
  error?: string | null;
}

@Injectable({ providedIn: 'root' })
export class AiService {

  private baseUrl = environment.backend;
  private http = inject(HttpClient);

  generateContent(model: string, question: string, length: string, style: string, mode: string): Observable<ContentGenerationResponse> {
    if (environment.useMock) {
      const mockData = mockReply(mode, { llm: model, question: question, length: length, style: style });

      return of({ success: true, data: mockData }).pipe(delay(1000));
    }

    const url = `${this.baseUrl}/llm/${mode}/${model}`;
    const body = { name: question, length: length, style: style };

    return this.http.post<ContentGenerationResponse>(url, body).pipe(
      catchError((error: HttpErrorResponse) => {
        console.error('Erreur API:', error);

        return of({
          success: false,
          data: '',
          error: this.getErrorMessage(error),
        });
      }),
    );
  }

  private getErrorMessage(error: HttpErrorResponse): string {
    if (error.status === 0) {
      return 'Serveur inaccessible. Vérifiez votre connexion.';
    }

    return `Erreur ${error.status}: ${error.message}`;
  }
}
