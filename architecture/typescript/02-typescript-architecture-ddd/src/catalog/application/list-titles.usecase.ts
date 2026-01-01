import { TitleRepository } from '../domain/title.repository'
import { Title } from '../domain/title'

export class ListTitlesUseCase {
  constructor(private readonly repository: TitleRepository) {}

  async execute(): Promise<Title[]> {
    return this.repository.findAll()
  }
}
