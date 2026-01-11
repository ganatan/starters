import { 
  Body, 
  Controller, 
  Delete, 
  Get, 
  Param, 
  ParseIntPipe, 
  Post, 
  Put,
  UsePipes,
  ValidationPipe
} from '@nestjs/common';
import { ContinentService } from './continent.service';
import { CreateContinentRequestDTO, UpdateContinentRequestDTO } from './continent.dto';

@UsePipes(new ValidationPipe({ whitelist: true, transform: true }))
@Controller('continents')
export class ContinentController {
  constructor(private readonly continentService: ContinentService) {}

  @Get()
  getAll() {
    return this.continentService.getAll();
  }

  @Get(':id')
  getById(@Param('id', ParseIntPipe) id: number) {
    return this.continentService.getById(id);
  }

  @Post()
  create(@Body() dto: CreateContinentRequestDTO) {
    return this.continentService.create(dto);
  }

  @Put(':id')
  update(
    @Param('id', ParseIntPipe) id: number,
    @Body() dto: UpdateContinentRequestDTO,
  ) {
    return this.continentService.update(id, dto);
  }

  @Delete(':id')
  delete(@Param('id', ParseIntPipe) id: number) {
    return this.continentService.delete(id);
  }
}
