import { Request, Response } from 'express'
import { ListTitlesUseCase } from '../../application/list-titles.usecase'

export class TitleController {
  constructor(private readonly listTitles: ListTitlesUseCase) {}

  async list(req: Request, res: Response) {
    const items = await this.listTitles.execute()
    res.json(items)
  }
}
