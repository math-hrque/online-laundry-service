package br.com.lol.lol.dtos;

import br.com.lol.lol.models.Roupa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoupaDTO {
    private Long idRoupa;
    private String descricao;
    private double preco;
    private int prazoDias;

    public RoupaDTO(Roupa roupa) {
        this.idRoupa = roupa.getIdRoupa();
        this.descricao = roupa.getDescricao();
        this.preco = roupa.getPreco();
        this.prazoDias = roupa.getPrazoDias();
    }
}
