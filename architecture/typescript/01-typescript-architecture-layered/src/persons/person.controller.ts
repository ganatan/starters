import { Router } from 'express'
import { Person } from './person.model'
import { PersonService } from './person.service'

export class PersonController {
  public readonly router = Router()

  constructor(private service: PersonService) {
    this.router.get('/', (req, res) => {
      const persons = this.service.listPersons()
      res.json(persons)
    })

    this.router.post('/', (req, res) => {
      const input = req.body as Omit<Person, 'id'>
      if (!input.firstName || !input.lastName || !input.role) {
        res.status(400).json({ error: 'Invalid payload' })
        return
      }
      const created = this.service.addPerson(input)
      res.status(201).json(created)
    })
  }
}
