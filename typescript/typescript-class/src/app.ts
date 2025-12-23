class Title {
  public name: string = '';
  franchise: boolean = false;
  budget: number = 0;
  readonly boxOffice: number;

  constructor() {
    console.log('Title:constructor:');
  }

  show(): void {
    console.log('Title:show:');
  }


  calculateBudget(): number {
    return 1000;
  }
}

class TitleBoxy extends Title {
  constructor() {
    super();
    console.log('TitleBoxy:constructor:');
  }

  override show() {
    super.show();
    console.log('TitleBoxy:show:');
  }
  override calculateBudget(): number {
    let result = super.calculateBudget();
    result = result + 100;
    return result;
  }
}

let title: TitleBoxy = new TitleBoxy();
title.name = "Black Rain";
title.franchise = false;
title.budget = 1234;

title.show();
console.log('00000000001:' + JSON.stringify(title));

let calculateBudget = title.calculateBudget();
console.log('00000000001:' + calculateBudget);

