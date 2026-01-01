import { Title } from './title'

export interface TitleRepository {
  findAll(): Promise<Title[]>
}
