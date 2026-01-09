import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { ContinentController } from './continent.controller';
import { ContinentService } from './continent.service';
import { ContinentRepository } from './continent.repository';

@Module({
  controllers: [AppController, ContinentController],
  providers: [AppService, ContinentService, ContinentRepository],
})
export class AppModule {}