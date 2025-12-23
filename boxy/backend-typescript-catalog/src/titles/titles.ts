// interface TitleContract {
//   relaseDate: Date,
// }

// interface TitleContract2 {
//   franchise: boolean,
// }

// class TitleModele implements TitleContract, TitleContract2 {
//   name: string;
//   relaseDate: Date;
//   franchise: boolean;
//   // constructor(name?: string) {
//   constructor() {
//     // if (name === undefined) { this.name = 'defaultName'; } else {
//     //   this.name = name;
//     // }
//     // this.franchise = false;
//     console.log('00000000001:TitleModele:constructor:');
//   }

//   // show() {
//   //   console.log('00000000001:TitleModele:show:' + this.name);
//   // }
// }

// class Title extends TitleModele {
//   // constructor(name?: string) {
//   constructor() {
//     super();
//     console.log('00000000001:Title:constructor:');
//   }

//   show() {
//     // super.show();
//     console.log('00000000001:Title:show:' + this.name);
//   }
// }

// function getTitles(): Title[] {
//   let titles: Title[] = [];
//   let toto: Title = new Title();
//   toto.show();
//   // titles.push(toto);
//   // titles.push(new Title());
//   // titles.push(new Title('Exodus'));
//   // titles.push(new Title('Legend'));
//   return titles;
// }


// export { getTitles };