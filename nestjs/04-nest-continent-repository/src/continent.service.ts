import { Injectable } from '@nestjs/common';
import { ContinentRepository } from './continent.repository';

@Injectable()
export class ContinentService {
  constructor(private readonly continentRepository: ContinentRepository) {}

  getAll() {
    return this.continentRepository.findAll();
  }
}