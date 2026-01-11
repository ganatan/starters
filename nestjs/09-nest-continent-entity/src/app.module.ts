import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { ContinentModule } from './modules/continent/continent.module';

@Module({
  imports: [ContinentModule],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}