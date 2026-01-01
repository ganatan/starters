import { CountryRepository } from './country.repository'
import { CountryService } from './country.service'
import { CountryController } from './country.controller'

const repository = new CountryRepository()
const service = new CountryService(repository)
const controller = new CountryController(service)

export const countryRouter = controller.router
