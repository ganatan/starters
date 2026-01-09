import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { ContinentController } from './continent.controller';
import { ContinentService } from './continent.service';

@Module({
  controllers: [AppController, ContinentController],
  providers: [AppService, ContinentService],
})
export class AppModule {}