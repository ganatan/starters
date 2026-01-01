import { Router } from 'express'

import { titlesMock } from '../../catalog/infrastructure/persistence/mocks/titles.mock'
import { InMemoryTitleRepository } from '../../catalog/infrastructure/persistence/mocks/in-memory-title.repository';

import { ListTitlesUseCase } from '../../catalog/application/list-titles.usecase'
import { TitleController } from '../../catalog/infrastructure/htpp/title.controller';
import { createTitleRouter } from '../../catalog/infrastructure/htpp/title.router';

export function buildTitleRoutes(): Router {
  const repository = new InMemoryTitleRepository(titlesMock)
  const listTitles = new ListTitlesUseCase(repository)
  const controller = new TitleController(listTitles)

  return createTitleRouter(controller)
}
