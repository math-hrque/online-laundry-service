import { Usuario } from './usuario.model';

export class Funcionario {
  idFuncionario: number = 0;
  dataNascimento: Date | null = null;
  usuario: Usuario = new Usuario();
}
