import { Country } from './country.model'
import { CountryRepository } from './country.repository'

export class CountryService {
  constructor(private repository: CountryRepository) {}

  listCountries(): Country[] {
    return this.repository.findAll()
  }

  addCountry(input: Omit<Country, 'id'>): Country {
    return this.repository.add(input)
  }
}
