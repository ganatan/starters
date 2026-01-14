import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-edit',
  imports: [],
  templateUrl: './edit.html',
  styleUrl: './edit.css',
})
export class Edit {
  @Input() value = '';
}