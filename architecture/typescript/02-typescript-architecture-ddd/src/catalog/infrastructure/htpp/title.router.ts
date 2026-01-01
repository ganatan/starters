import { Router } from 'express'
import { TitleController } from './title.controller'

export function createTitleRouter(controller: TitleController): Router {
  const router = Router()

  router.get('/', (req, res) => controller.list(req, res))

  return router
}
