package br.com.lol.lol.dtos;

import java.time.LocalDate;

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
}
