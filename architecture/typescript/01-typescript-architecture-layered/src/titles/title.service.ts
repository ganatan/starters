import { Title } from './title.model'
import { TitleRepository } from './title.repository'

export class TitleService {
  constructor(private repository: TitleRepository) {}

  listTitles(): Title[] {
    return this.repository.findAll()
  }

  addTitle(input: Omit<Title, 'id'>): Title {
    return this.repository.add(input)
  }
}
