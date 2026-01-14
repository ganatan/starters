import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { ContinentController } from './continent.controller';

@Module({
  controllers: [AppController, ContinentController],
})
export class AppModule {}
