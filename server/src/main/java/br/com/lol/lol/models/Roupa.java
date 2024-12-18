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
@Table(name="roupa")
public class Roupa implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_roupa")
    private Long idRoupa = 0L;

    @Column(name="descricao", nullable = false)
    private String descricao;

    @Column(name="preco", nullable = false)
    private double preco;

    @Column(name="prazo_dias", nullable = false)
    private int prazoDias;

    @Column(name="ativo", insertable = false)
    private boolean ativo = true;

    public void inativar() {
        this.ativo = false;
    }
}
