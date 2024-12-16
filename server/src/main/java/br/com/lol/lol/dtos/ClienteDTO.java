package br.com.lol.lol.dtos;

import br.com.lol.lol.models.Cliente;
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

    public ClienteDTO(Cliente cliente) {
        this.idCliente = cliente.getIdCliente();
        this.cpf = cliente.getCpf();
        this.telefone = cliente.getTelefone();
        this.endereco = new EnderecoDTO(cliente.getEndereco());
        this.usuario = new UsuarioResponseDTO(cliente.getUsuario());
    }
}
