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
  UseFilters,
  UsePipes,
  ValidationPipe,
} from '@nestjs/common';
import { ContinentRequestDTO } from './continent.dto';
import { HttpErrorFilter } from './http-error.filter';

type Continent = {
  id: number;
  name: string;
};

@Controller(['continents', 'continents/'])
@UseFilters(HttpErrorFilter)
@UsePipes(new ValidationPipe({ whitelist: true, forbidNonWhitelisted: true, transform: true }))
export class ContinentController {
  private idCounter = 0;
  private continents: Continent[] = [];

  constructor() {
    ['Africa', 'America', 'Asia', 'Europe', 'Oceania', 'Antarctica']
      .forEach(name => this.create(name));
  }

  @Get()
  getAllContinents(): Continent[] {
    return this.continents;
  }

  @Get(':id')
  getContinentById(@Param('id', ParseIntPipe) id: number): Continent {
    return this.requireById(id);
  }

  @Post()
  @HttpCode(HttpStatus.CREATED)
  createContinent(@Body() body: ContinentRequestDTO): Continent {
    return this.create(body.name);
  }

  @Put(':id')
  updateContinent(
    @Param('id', ParseIntPipe) id: number,
    @Body() body: ContinentRequestDTO,
  ): Continent {
    const index = this.requireIndexById(id);
    const updated: Continent = { id, name: body.name };
    this.continents[index] = updated;
    return updated;
  }

  @Delete(':id')
  @HttpCode(HttpStatus.NO_CONTENT)
  deleteContinent(@Param('id', ParseIntPipe) id: number): void {
    const index = this.requireIndexById(id);
    this.continents.splice(index, 1);
  }

  private create(name: string): Continent {
    const continent: Continent = { id: ++this.idCounter, name };
    this.continents.push(continent);
    return continent;
  }

  private requireById(id: number): Continent {
    const found = this.continents.find(c => c.id === id);
    if (!found) {
      throw new NotFoundException();
    }
    return found;
  }

  private requireIndexById(id: number): number {
    const index = this.continents.findIndex(c => c.id === id);
    if (index === -1) {
      throw new NotFoundException();
    }
    return index;
  }
}
