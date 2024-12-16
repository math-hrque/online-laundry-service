package br.com.lol.lol.models;

import java.io.Serializable;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orcamento")
public class Orcamento implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_orcamento")
    private Long idOrcamento;

    @Column(name="valor")
    private Double valor;

    @Temporal(TemporalType.DATE)
    @Column(name="data_prazo")
    private LocalDate dataPrazo;

    @Column(name="aprovado")
    private boolean aprovado;
}
