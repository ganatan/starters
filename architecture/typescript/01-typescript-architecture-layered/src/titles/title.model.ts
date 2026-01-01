export type TitleType = 'MOVIE' | 'SERIES'

export interface Title {
  id: number
  name: string
  type: TitleType
  year: number
  countryCode: string
}
