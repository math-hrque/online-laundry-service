package br.com.lol.lol.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private Long idCliente;
    private String cpf;
    private String telefone;
    private EnderecoDTO endereco;
    private UsuarioResponseDTO usuario;
}
