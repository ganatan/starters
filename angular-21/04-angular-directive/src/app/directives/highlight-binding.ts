import { Directive } from '@angular/core';

import {  HostBinding, HostListener } from '@angular/core';

@Directive({
  selector: '[appHighlightBinding]'
})
export class HighlightBinding {

  @HostBinding('style.background') bg = ''

  @HostListener('mouseenter')
  enter() {
    this.bg = 'yellow'
  }

  @HostListener('mouseleave')
  leave() {
    this.bg = 'red'
  }  

}
