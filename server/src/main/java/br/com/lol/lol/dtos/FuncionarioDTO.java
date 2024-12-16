package br.com.lol.lol.dtos;

import java.time.LocalDate;

import br.com.lol.lol.models.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDTO {
    private Long idFuncionario;
    private LocalDate dataNascimento;
    private UsuarioResponseDTO usuario;

    public FuncionarioDTO(Funcionario funcionario) {
        this.idFuncionario = funcionario.getIdFuncionario();
        this.dataNascimento = funcionario.getDataNascimento();
        this.usuario = new UsuarioResponseDTO(funcionario.getUsuario());
    }
}
