package br.com.lol.lol.models;

import java.io.Serializable;

import br.com.lol.lol.enums.TipoSituacao;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="situacao")
public class Situacao implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_situacao")
    private Long idSituacao;

    @Enumerated(EnumType.STRING)
    @Column(name="tipo_situacao", insertable = false, updatable = false, nullable = false, unique = true)
    private TipoSituacao tipoSituacao;
}
