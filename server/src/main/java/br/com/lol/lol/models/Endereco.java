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
@Table(name="endereco")
public class Endereco implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_endereco")
    private Long idEndereco = 0L;

    @Column(name="cep", nullable = false)
    private String cep;

    @Column(name="estado", nullable = false)
    private String estado;

    @Column(name="cidade", nullable = false)
    private String cidade;

    @Column(name="bairro", nullable = false)
    private String bairro;

    @Column(name="rua", nullable = false)
    private String rua;

    @Column(name="numero", nullable = false)
    private String numero;

    @Column(name="complemento")
    private String complemento;
}
