import { Test, TestingModule } from '@nestjs/testing';
import { AppController } from './app.controller';
import { AppService } from './app.service';

describe('AppController', () => {
  let appController: AppController;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      controllers: [AppController],
      providers: [
        {
          provide: AppService,
          useValue: {
            getStatus: () => ({ status: 'ok' }),
          },
        },
      ],
    }).compile();

    appController = module.get<AppController>(AppController);
  });

  it('should return status ok', () => {
    expect(appController.get()).toEqual({ status: 'ok' });
  });
});