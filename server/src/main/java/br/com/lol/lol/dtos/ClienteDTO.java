package br.com.lol.lol.dtos;

import br.com.lol.lol.models.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    @Setter @Getter
    private Long idCliente;

    @Setter @Getter
    private String cpf;

    @Setter @Getter
    private String telefone;

    @Setter @Getter
    private EnderecoDTO endereco;

    @Setter @Getter
    private UsuarioResponseDTO usuario;

    public ClienteDTO(Cliente cliente) {
        this.idCliente = cliente.getIdCliente();
        this.cpf = cliente.getCpf();
        this.telefone = cliente.getTelefone();
        this.endereco = new EnderecoDTO(cliente.getEndereco());
        this.usuario = new UsuarioResponseDTO(cliente.getUsuario());
    }
}
