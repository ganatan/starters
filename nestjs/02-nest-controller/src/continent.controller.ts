import {
  Body, Controller, Delete, Get, HttpCode, HttpStatus, NotFoundException, Param,
  ParseIntPipe, Post, Put,
} from '@nestjs/common';

type Continent = {
  id: number;
  name: string;
};

@Controller('continents')
export class ContinentController {
  private idCounter = 0;
  private continentList: Continent[] = [];

  constructor() {
    ['Africa', 'America', 'Asia', 'Europe', 'Oceania', 'Antarctica']
      .forEach(name => this.createContinent({ name }));
  }

  @Get()
  getAllContinents(): Continent[] {
    return this.continentList;
  }

  @Get(':id')
  getContinentById(@Param('id', ParseIntPipe) id: number): Continent {
    const continent = this.findContinentById(id);
    if (!continent) {
      throw new NotFoundException();
    }
    return continent;
  }

  @Post()
  @HttpCode(HttpStatus.CREATED)
  createContinent(@Body() body: { name: string }): Continent {
    const continent: Continent = {
      id: ++this.idCounter,
      name: body.name,
    };
    this.continentList.push(continent);
    return continent;
  }

  @Put(':id')
  updateContinent(
    @Param('id', ParseIntPipe) id: number,
    @Body() body: { name: string },
  ): Continent {
    const index = this.findIndexById(id);
    if (index === -1) {
      throw new NotFoundException();
    }
    const updated: Continent = { id, name: body.name };
    this.continentList[index] = updated;
    return updated;
  }

  @Delete(':id')
  @HttpCode(HttpStatus.NO_CONTENT)
  deleteContinent(@Param('id', ParseIntPipe) id: number): void {
    const index = this.findIndexById(id);
    if (index === -1) {
      throw new NotFoundException();
    }
    this.continentList.splice(index, 1);
  }

  private findContinentById(id: number): Continent | undefined {
    return this.continentList.find(c => c.id === id);
  }

  private findIndexById(id: number): number {
    return this.continentList.findIndex(c => c.id === id);
  }
}