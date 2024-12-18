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
@Table(name="cliente")
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_cliente")
    private Long idCliente;

    @Column(name="cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name="telefone", nullable = false)
    private String telefone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="id_endereco", nullable = false)
    private Endereco endereco;
}
