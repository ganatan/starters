import { Router } from 'express'
import { buildTitleRoutes } from './routes/title.routes'

export function buildRoutes(): Router {
  const router = Router()

  router.use('/titles', buildTitleRoutes())

  return router
}
