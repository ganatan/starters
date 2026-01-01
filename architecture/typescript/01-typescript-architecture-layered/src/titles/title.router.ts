import { TitleRepository } from './title.repository'
import { TitleService } from './title.service'
import { TitleController } from './title.controller'

const repository = new TitleRepository()
const service = new TitleService(repository)
const controller = new TitleController(service)

export const titleRouter = controller.router
