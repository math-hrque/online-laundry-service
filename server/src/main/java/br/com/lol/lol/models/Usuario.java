package br.com.lol.lol.models;

import java.io.Serializable;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="usuario")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Long idUsuario;

    @Column(name="email", unique = true)
    private String email;

    @Column(name="senha")
    private String senha;

    @Column(name="nome")
    private String nome;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_permissao")
    private Permissao permissao;
}
