import { Directive, ElementRef, Renderer2, HostListener } from '@angular/core';

@Directive({
  selector: '[appHighlightRenderer]',
  standalone: true
})
export class HighlightRenderer {
  constructor(private el: ElementRef, private renderer: Renderer2) {}

  @HostListener('mouseenter')
  enter() {
    this.renderer.setStyle(this.el.nativeElement, 'background', 'yellow')
  }

  @HostListener('mouseleave')
  leave() {
    this.renderer.setStyle(this.el.nativeElement, 'background', 'red')
  }
}

