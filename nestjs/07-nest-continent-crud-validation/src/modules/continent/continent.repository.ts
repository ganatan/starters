import { Injectable } from '@nestjs/common';

@Injectable()
export class ContinentRepository {
  private continents = [
    { id: 1, name: 'Europe' },
    { id: 2, name: 'Asia' },
    { id: 3, name: 'Africa' },
    { id: 4, name: 'America' },
    { id: 5, name: 'Oceania' },
    { id: 6, name: 'Antarctica' },
  ];

  private currentId = 7;

  findAll() {
    return this.continents;
  }

  findById(id: number) {
    return this.continents.find((c) => c.id === id);
  }

  create(data: any) {
    const newContinent = {
      ...data,
      id: this.currentId++,
    };
    this.continents.push(newContinent);
    return newContinent;
  }

  update(id: number, data: any) {
    const index = this.continents.findIndex((c) => c.id === id);
    if (index === -1) return undefined;

    this.continents[index] = { ...this.continents[index], ...data };
    return this.continents[index];
  }

  delete(id: number) {
    const index = this.continents.findIndex((c) => c.id === id);
    if (index === -1) return false;

    this.continents.splice(index, 1);
    return true;
  }
}