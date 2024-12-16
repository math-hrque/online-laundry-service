package br.com.lol.lol.dtos;

import br.com.lol.lol.enums.TipoPermissao;
import br.com.lol.lol.models.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {
    private Long idUsuario;
    private String email;
    private String nome;
    private TipoPermissao tipoPermissao;

    public UsuarioResponseDTO(Usuario usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.email = usuario.getEmail();
        this.nome = usuario.getNome();
        this.tipoPermissao = usuario.getPermissao().getTipoPermissao();
    }
}
