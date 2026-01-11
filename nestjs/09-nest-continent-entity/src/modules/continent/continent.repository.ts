import { Injectable } from '@nestjs/common';
import { Continent } from './continent.entity';

export const CONTINENT_REPOSITORY = 'ContinentRepository';

export interface ContinentRepository {
  findAll(): Promise<Continent[]>;
  findById(id: number): Promise<Continent | null>;
  findByName(name: string): Promise<Continent | null>;
  create(name: string): Promise<Continent>;
  update(id: number, name?: string): Promise<Continent | null>;
  delete(id: number): Promise<boolean>;
}

@Injectable()
export class InMemoryContinentRepository implements ContinentRepository {
  private continents: Continent[] = [
    new Continent(1, 'Europe'),
    new Continent(2, 'Asia'),
    new Continent(3, 'Africa'),
    new Continent(4, 'America'),
    new Continent(5, 'Oceania'),
    new Continent(6, 'Antarctica'),
  ];

  private currentId = 7;

  async findAll(): Promise<Continent[]> {
    return [...this.continents];
  }

  async findById(id: number): Promise<Continent | null> {
    return this.continents.find((c) => c.id === id) ?? null;
  }

  async findByName(name: string): Promise<Continent | null> {
    const n = name.trim().toLowerCase();
    return this.continents.find((c) => c.name.trim().toLowerCase() === n) ?? null;
  }

  async create(name: string): Promise<Continent> {
    const entity = new Continent(this.currentId++, name);
    this.continents.push(entity);
    return entity;
  }

  async update(id: number, name?: string): Promise<Continent | null> {
    const entity = await this.findById(id);
    if (!entity) return null;
    if (typeof name === 'string') entity.name = name;
    return entity;
  }

  async delete(id: number): Promise<boolean> {
    const index = this.continents.findIndex((c) => c.id === id);
    if (index === -1) return false;
    this.continents.splice(index, 1);
    return true;
  }
}
