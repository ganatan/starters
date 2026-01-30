import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { AiService, ContentGenerationResponse } from '../app/core/services/ai-service';
import { environment } from '../environments/environment';

@Component({
  selector: 'app-root',
  imports: [FormsModule, CommonModule],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  question = 'Quels sont les thèmes récurrents dans les films de Christopher Nolan ?';
  model = 'chatgpt';
  mode = 'rag';
  style = 'neutral';
  length = 'medium';

  content = '';
  loading = false;
  error: string | null = null;

  duration = 0;
  progress = 0;

  useMock = environment.useMock;

  styleOptions = [
    { value: 'neutral', label: 'Neutre' },
    { value: 'cinematic', label: 'Cinématographique' },
    { value: 'humorous', label: 'Humoristique' },
    { value: 'technical', label: 'Technique' },
    { value: 'historical', label: 'Historique' },
    { value: 'poetic', label: 'Poétique' },
  ];

  private aiService = inject(AiService);

  toggleTheme() {
    document.body.classList.toggle('dark-mode');
    document.documentElement.classList.toggle('dark-mode');
  }

  loadContent() {
    const start = performance.now();
    const interval = this.startProgress();

    this.resetContent();
    this.loading = true;

    this.aiService
      .generateContent(this.model, this.question, this.length, this.style, this.mode)
      .subscribe((response: ContentGenerationResponse) => {
        clearInterval(interval);
        this.duration = (performance.now() - start) / 1000;
        this.error = response.success ? null : response.error || null;
        this.content = response.data;
        this.loading = false;
        this.progress = 100;
      });
  }

  resetContent() {
    this.error = null;
    this.content = '';
    this.duration = 0;
    this.progress = 0;
    this.loading = false;
  }

  onStyleChange(value: string) {
    this.style = value;
    this.resetContent();
  }

  onLengthChange(value: string) {
    this.length = value;
    this.resetContent();
  }

  onModelChange(value: string) {
    this.model = value;
    this.resetContent();
  }

  onModeChange(value: string) {
    this.mode = value;
    this.resetContent();
  }

  private startProgress() {
    let progress = 0;
    const interval = setInterval(() => {
      progress += 5;
      if (progress < 95) {
        this.progress = progress;
      }
    }, 100);

    return interval;
  }
}
