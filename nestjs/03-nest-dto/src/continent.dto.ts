import { IsNotEmpty, IsString, MaxLength } from 'class-validator';

export class ContinentRequestDTO {
  @IsString({ message: 'name is required' })
  @IsNotEmpty({ message: 'name is required' })
  @MaxLength(50, { message: 'name must not exceed 50 characters' })
  name!: string;
}
