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
  UsePipes,
  ValidationPipe,
} from '@nestjs/common';
import { IsNotEmpty, IsString, MaxLength } from 'class-validator';

type Continent = { id: number; name: string };

class CreateContinentRequestDTO {
  @IsString()
  @IsNotEmpty()
  @MaxLength(50)
  name!: string;
}

class UpdateContinentRequestDTO {
  @IsString()
  @IsNotEmpty()
  @MaxLength(50)
  name!: string;
}

@UsePipes(
  new ValidationPipe({
    whitelist: true,
    transform: true,
    forbidNonWhitelisted: true,
  }),
)
@Controller('continents')
export class ContinentController {
  private idCounter = 0;
  private continentList: Continent[] = [];

  constructor() {
    this.seed('Africa', 'America', 'Asia', 'Europe', 'Oceania', 'Antarctica');
  }

  @Get()
  getAll(): Continent[] {
    return this.continentList;
  }

  @Get(':id')
  getById(@Param('id', ParseIntPipe) id: number): Continent {
    return this.findRequiredById(id);
  }

  @Post()
  @HttpCode(HttpStatus.CREATED)
  create(@Body() dto: CreateContinentRequestDTO): Continent {
    const created: Continent = { id: this.nextId(), name: this.normalize(dto.name) };
    this.continentList.push(created);
    return created;
  }

  @Put(':id')
  update(
    @Param('id', ParseIntPipe) id: number,
    @Body() dto: UpdateContinentRequestDTO,
  ): Continent {
    const index = this.findIndexById(id);
    if (index === -1) throw new NotFoundException();
    const updated: Continent = { id, name: this.normalize(dto.name) };
    this.continentList[index] = updated;
    return updated;
  }

  @Delete(':id')
  @HttpCode(HttpStatus.NO_CONTENT)
  delete(@Param('id', ParseIntPipe) id: number): void {
    const index = this.findIndexById(id);
    if (index === -1) throw new NotFoundException();
    this.continentList.splice(index, 1);
  }

  private seed(...names: string[]): void {
    for (const name of names) {
      this.continentList.push({ id: this.nextId(), name: this.normalize(name) });
    }
  }

  private nextId(): number {
    return ++this.idCounter;
  }

  private normalize(name: string): string {
    return name.trim();
  }

  private findRequiredById(id: number): Continent {
    if (id <= 0) throw new NotFoundException();
    const found = this.continentList.find(c => c.id === id);
    if (!found) throw new NotFoundException();
    return found;
  }

  private findIndexById(id: number): number {
    if (id <= 0) return -1;
    return this.continentList.findIndex(c => c.id === id);
  }
}
