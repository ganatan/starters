import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'budget',
})
export class BudgetPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    const number = Number(value) || 0;
    return `$${number.toLocaleString('en-US')}`;
  }

}