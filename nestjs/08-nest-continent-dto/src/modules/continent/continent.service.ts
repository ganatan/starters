import {
  BadRequestException,
  Injectable,
  NotFoundException,
} from '@nestjs/common';
import { ContinentRepository } from './continent.repository';
import { CreateContinentDto, UpdateContinentDto } from './continent.dto';

@Injectable()
export class ContinentService {
  constructor(private readonly continentRepository: ContinentRepository) {}

  getAll() {
    return this.continentRepository.findAll();
  }

  getById(id: number) {
    const continent = this.continentRepository.findById(id);
    if (!continent) {
      throw new NotFoundException(`Continent ${id} not found`);
    }
    return continent;
  }

  create(dto: CreateContinentDto) {
    if (!dto.name) {
      throw new BadRequestException('name is required');
    }
    return this.continentRepository.create({ name: dto.name });
  }

  update(id: number, dto: UpdateContinentDto) {
    if (Object.keys(dto).length === 0) {
      throw new BadRequestException('at least one field is required');
    }

    const updated = this.continentRepository.update(id, dto);
    if (!updated) {
      throw new NotFoundException(`Continent ${id} not found`);
    }
    return updated;
  }

  delete(id: number) {
    const deleted = this.continentRepository.delete(id);
    if (!deleted) {
      throw new NotFoundException(`Continent ${id} not found`);
    }
    return true;
  }
}