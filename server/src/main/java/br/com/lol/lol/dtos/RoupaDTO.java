package br.com.lol.lol.dtos;

import br.com.lol.lol.models.Roupa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class RoupaDTO {
    @Setter @Getter
    private Long idRoupa;

    @Setter @Getter
    private String descricao;

    @Setter @Getter
    private double preco;

    @Setter @Getter
    private int prazoDias;

    public RoupaDTO(Roupa roupa) {
        this.idRoupa = roupa.getIdRoupa();
        this.descricao = roupa.getDescricao();
        this.preco = roupa.getPreco();
        this.prazoDias = roupa.getPrazoDias();
    }
}
