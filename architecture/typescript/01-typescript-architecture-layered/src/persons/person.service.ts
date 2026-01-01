import { Person } from './person.model'
import { PersonRepository } from './person.repository'

export class PersonService {
  constructor(private repository: PersonRepository) {}

  listPersons(): Person[] {
    return this.repository.findAll()
  }

  addPerson(input: Omit<Person, 'id'>): Person {
    return this.repository.add(input)
  }
}
