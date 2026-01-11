import { IsNotEmpty, IsOptional, IsString, Length } from 'class-validator';

export class CreateContinentRequestDTO {
  @IsString({ message: 'le nom doit être une chaîne de caractères' })
  @IsNotEmpty({ message: 'le nom du continent est obligatoire' })
  @Length(4, 60, {
    message: 'le nom du continent doit contenir entre $constraint1 et $constraint2 caractères',
  })
  name!: string;
}

export class UpdateContinentRequestDTO {
  @IsOptional()
  @IsString({ message: 'le nom doit être une chaîne de caractères' })
  @IsNotEmpty({ message: 'le nom du continent ne peut pas être vide' })
  @Length(2, 60, {
    message: 'le nom du continent doit contenir entre $constraint1 et $constraint2 caractères',
  })
  name?: string;
}

export class ContinentResponseDTO {
  id!: number;
  name!: string;
}

export function toContinentResponseDTO(
  entity: { id: number; name: string },
): ContinentResponseDTO {
  return {
    id: entity.id,
    name: entity.name,
  };
}
