// src/modules/continent/continent.module.ts
import { Module } from '@nestjs/common';
import { ContinentController } from './continent.controller';
import { ContinentService } from './continent.service';
import { CONTINENT_REPOSITORY, InMemoryContinentRepository } from './continent.repository';

@Module({
  controllers: [ContinentController],
  providers: [
    ContinentService,
    { provide: CONTINENT_REPOSITORY, useClass: InMemoryContinentRepository },
  ],
  exports: [ContinentService],
})
export class ContinentModule {}
