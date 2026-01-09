import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { ContinentController } from './continent.controller';

@Module({
  controllers: [AppController, ContinentController],
  providers: [AppService],
})
export class AppModule {}