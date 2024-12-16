package br.com.lol.lol.models;

import java.io.Serializable;

import br.com.lol.lol.enums.TipoPermissao;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="permissao")
public class Permissao implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_permissao")
    private Long idPermissao;

    @Enumerated(EnumType.STRING)
    @Column(name="tipo_permissao", insertable = false, updatable = false, nullable = false, unique = true)
    private TipoPermissao tipoPermissao;
}
