import { PersonRepository } from './person.repository'
import { PersonService } from './person.service'
import { PersonController } from './person.controller'

const repository = new PersonRepository()
const service = new PersonService(repository)
const controller = new PersonController(service)

export const personRouter = controller.router
