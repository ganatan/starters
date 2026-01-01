import { Title } from './title.model'

export class TitleRepository {
  private titles: Title[] = [
    { id: 1, name: 'Inception', type: 'MOVIE', year: 2010, countryCode: 'US' },
    { id: 2, name: 'The Dark Knight', type: 'MOVIE', year: 2008, countryCode: 'US' },
    { id: 3, name: 'La Haine', type: 'MOVIE', year: 1995, countryCode: 'FR' },
    { id: 4, name: 'Breaking Bad', type: 'SERIES', year: 2008, countryCode: 'US' }
  ]

  findAll(): Title[] {
    return this.titles
  }

  add(title: Omit<Title, 'id'>): Title {
    const nextId = this.titles.length > 0 ? Math.max(...this.titles.map(t => t.id)) + 1 : 1
    const created: Title = { id: nextId, ...title }
    this.titles.push(created)
    return created
  }
}
