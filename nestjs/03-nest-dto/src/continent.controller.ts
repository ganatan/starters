import {
  Body,
  Controller,
  Delete,
  Get,
  HttpCode,
  HttpStatus,
  NotFoundException,
  Param,
  ParseIntPipe,
  Post,
  Put,
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
    this.createInternal('Africa');
    this.createInternal('America');
    this.createInternal('Asia');
    this.createInternal('Europe');
    this.createInternal('Oceania');
    this.createInternal('Antarctica');
  }

  @Get()
  getAll(): Continent[] {
    return this.continentList;
  }

  @Get(':id')
  getById(@Param('id', ParseIntPipe) id: number): Continent {
    const continent = this.findById(id);
    if (!continent) {
      throw new NotFoundException();
    }
    return continent;
  }

  @Post()
  @HttpCode(HttpStatus.CREATED)
  create(@Body() body: { name: string }): Continent {
    const continent: Continent = {
      id: ++this.idCounter,
      name: body.name,
    };
    this.continentList.push(continent);
    return continent;
  }

  @Put(':id')
  update(
    @Param('id', ParseIntPipe) id: number,
    @Body() body: { name: string },
  ): Continent {
    const index = this.continentList.findIndex(c => c.id === id);
    if (index === -1) {
      throw new NotFoundException();
    }
    const updated: Continent = { id, name: body.name };
    this.continentList[index] = updated;
    return updated;
  }

  @Delete(':id')
  @HttpCode(HttpStatus.NO_CONTENT)
  delete(@Param('id', ParseIntPipe) id: number): void {
    const index = this.continentList.findIndex(c => c.id === id);
    if (index === -1) {
      throw new NotFoundException();
    }
    this.continentList.splice(index, 1);
  }

  private findById(id: number): Continent | undefined {
    return this.continentList.find(c => c.id === id);
  }

  private createInternal(name: string): void {
    this.continentList.push({
      id: ++this.idCounter,
      name,
    });
  }
}
