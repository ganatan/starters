import { TitleRepository } from '../../../domain/title.repository'
import { Title } from '../../../domain/title'

export class InMemoryTitleRepository implements TitleRepository {
  constructor(private readonly items: Title[]) {}

  async findAll(): Promise<Title[]> {
    return this.items
  }
}
