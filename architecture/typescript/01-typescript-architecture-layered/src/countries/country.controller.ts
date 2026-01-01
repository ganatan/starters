import { Router } from 'express'
import { Country } from './country.model'
import { CountryService } from './country.service'

export class CountryController {
  public readonly router = Router()

  constructor(private service: CountryService) {
    this.router.get('/', (req, res) => {
      const countries = this.service.listCountries()
      res.json(countries)
    })

    this.router.post('/', (req, res) => {
      const input = req.body as Omit<Country, 'id'>
      if (!input.code || !input.name) {
        res.status(400).json({ error: 'Invalid payload' })
        return
      }
      const created = this.service.addCountry(input)
      res.status(201).json(created)
    })
  }
}
