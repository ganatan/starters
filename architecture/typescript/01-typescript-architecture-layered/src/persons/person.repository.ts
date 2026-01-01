import { Person } from './person.model'

export class PersonRepository {
  private persons: Person[] = [
    { id: 1, firstName: 'Christopher', lastName: 'Nolan', role: 'Director' },
    { id: 2, firstName: 'Bryan', lastName: 'Cranston', role: 'Actor' },
    { id: 3, firstName: 'Mathieu', lastName: 'Kassovitz', role: 'Director' }
  ]

  findAll(): Person[] {
    return this.persons
  }

  add(person: Omit<Person, 'id'>): Person {
    const nextId = this.persons.length > 0 ? Math.max(...this.persons.map(p => p.id)) + 1 : 1
    const created: Person = { id: nextId, ...person }
    this.persons.push(created)
    return created
  }
}
