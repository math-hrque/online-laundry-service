package br.com.lol.lol.dtos;

import br.com.lol.lol.models.PedidoRoupa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRoupaDTO {
    private int quantidade;
    private RoupaDTO roupa;

    public PedidoRoupaDTO(PedidoRoupa pedidoRoupa) {
        this.quantidade = pedidoRoupa.getQuantidade();
        this.roupa = new RoupaDTO(pedidoRoupa.getRoupa());
    }
}
