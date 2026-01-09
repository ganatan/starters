import { Controller, Get } from '@nestjs/common';

@Controller('continents')
export class ContinentController {
  @Get()
  getAll() {
    return [
      { id: 1, name: 'Europe' },
      { id: 2, name: 'Asia' },
      { id: 3, name: 'Africa' },
      { id: 4, name: 'America' },
      { id: 5, name: 'Oceania' },
      { id: 6, name: 'Antarctica' },
    ];
  }
}
