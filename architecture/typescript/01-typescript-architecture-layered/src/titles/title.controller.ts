import { Router } from 'express'
import { Title } from './title.model'
import { TitleService } from './title.service'

export class TitleController {
  public readonly router = Router()

  constructor(private service: TitleService) {
    this.router.get('/', (req, res) => {
      const titles = this.service.listTitles()
      res.json(titles)
    })

    this.router.post('/', (req, res) => {
      const input = req.body as Omit<Title, 'id'>
      if (!input.name || !input.type || !input.year || !input.countryCode) {
        res.status(400).json({ error: 'Invalid payload' })
        return
      }
      const created = this.service.addTitle(input)
      res.status(201).json(created)
    })
  }
}
