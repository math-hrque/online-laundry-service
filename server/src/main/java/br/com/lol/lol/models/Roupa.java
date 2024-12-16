package br.com.lol.lol.models;

import java.io.Serializable;

import br.com.lol.lol.dtos.RoupaDTO;
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
    private Long idRoupa;

    @Column(name="descricao", nullable = false)
    private String descricao;

    @Column(name="preco", nullable = false)
    private double preco;

    @Column(name="prazo_dias", nullable = false)
    private int prazoDias;

    @Column(name="ativo", insertable = false)
    private boolean ativo = true;

    public void cadastrar(RoupaDTO roupaDTO) {
        this.idRoupa = 0L;
        this.descricao = roupaDTO.getDescricao();
        this.preco = roupaDTO.getPreco();
        this.prazoDias = roupaDTO.getPrazoDias();
    }

    public void atualizar(Long idRoupa, RoupaDTO roupaDTO) {
        this.idRoupa = idRoupa;
        this.descricao = roupaDTO.getDescricao();
        this.preco = roupaDTO.getPreco();
        this.prazoDias = roupaDTO.getPrazoDias();
    }

    public void inativar() {
        this.ativo = false;
    }
}
