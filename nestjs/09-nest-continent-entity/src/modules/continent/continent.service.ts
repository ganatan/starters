import { BadRequestException, ConflictException, Inject, Injectable, NotFoundException } from '@nestjs/common';
import { ContinentResponseDTO, CreateContinentRequestDTO, UpdateContinentRequestDTO, toContinentResponseDTO } from './continent.dto';
import { CONTINENT_REPOSITORY } from './continent.repository';
import type { ContinentRepository } from './continent.repository';

@Injectable()
export class ContinentService {
  constructor(
    @Inject(CONTINENT_REPOSITORY)
    private readonly continentRepository: ContinentRepository,
  ) {}

  async getAll(): Promise<ContinentResponseDTO[]> {
    const items = await this.continentRepository.findAll();
    return items.map(toContinentResponseDTO);
  }

  async getById(id: number): Promise<ContinentResponseDTO> {
    const continent = await this.continentRepository.findById(id);
    if (!continent) throw new NotFoundException(`Continent ${id} not found`);
    return toContinentResponseDTO(continent);
  }

  async create(dto: CreateContinentRequestDTO): Promise<ContinentResponseDTO> {
    const name = dto.name.trim();
    const exists = await this.continentRepository.findByName(name);
    if (exists) throw new ConflictException('Continent name already exists');
    const created = await this.continentRepository.create(name);
    return toContinentResponseDTO(created);
  }

  async update(id: number, dto: UpdateContinentRequestDTO): Promise<ContinentResponseDTO> {
    if (!dto.name) throw new BadRequestException('at least one field is required');

    const current = await this.continentRepository.findById(id);
    if (!current) throw new NotFoundException(`Continent ${id} not found`);

    const name = dto.name.trim();
    const byName = await this.continentRepository.findByName(name);
    if (byName && byName.id !== id) throw new ConflictException('Continent name already exists');

    const updated = await this.continentRepository.update(id, name);
    if (!updated) throw new NotFoundException(`Continent ${id} not found`);

    return toContinentResponseDTO(updated);
  }

  async delete(id: number): Promise<{ deleted: true }> {
    const deleted = await this.continentRepository.delete(id);
    if (!deleted) throw new NotFoundException(`Continent ${id} not found`);
    return { deleted: true };
  }
}
