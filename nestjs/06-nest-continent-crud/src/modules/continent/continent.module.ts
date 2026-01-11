import { Module } from '@nestjs/common';
import { ContinentController } from './continent.controller';
import { ContinentService } from './continent.service';
import { ContinentRepository } from './continent.repository';

@Module({
  controllers: [ContinentController],
  providers: [ContinentService, ContinentRepository],
  exports: [ContinentService],
})
export class ContinentModule {}