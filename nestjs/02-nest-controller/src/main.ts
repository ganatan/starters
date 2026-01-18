import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  app.enableCors({
    origin: 'http://localhost:4200',
    credentials: true,
  });

  const port = Number(process.env.PORT ?? 3000);
  await app.listen(port);

  console.log(`Server started on port ${port}`);
}
bootstrap();



// import { NestFactory } from '@nestjs/core';
// import { AppModule } from './app.module';

// async function bootstrap() {
//   const app = await NestFactory.create(AppModule);

//   const port = Number(process.env.PORT ?? 3000);
//   await app.listen(port);

//   console.log(`Server started on port ${port}`);
// }
// bootstrap();