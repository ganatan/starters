import { Country } from './country.model'

export class CountryRepository {
  private countries: Country[] = [
    { id: 1, code: 'US', name: 'United States' },
    { id: 2, code: 'FR', name: 'France' },
    { id: 3, code: 'GB', name: 'United Kingdom' }
  ]

  findAll(): Country[] {
    return this.countries
  }

  add(country: Omit<Country, 'id'>): Country {
    const nextId = this.countries.length > 0 ? Math.max(...this.countries.map(c => c.id)) + 1 : 1
    const created: Country = { id: nextId, ...country }
    this.countries.push(created)
    return created
  }
}
