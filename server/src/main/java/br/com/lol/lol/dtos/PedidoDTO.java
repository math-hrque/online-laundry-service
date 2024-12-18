package br.com.lol.lol.dtos;

import java.time.OffsetDateTime;
import java.util.List;

import br.com.lol.lol.enums.TipoSituacao;
import br.com.lol.lol.models.Orcamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private Long numeroPedido;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataPagamento;
    private Long idCliente;
    private TipoSituacao situacao;
    private Orcamento orcamento;
    private List<PedidoRoupaDTO> listaPedidoRoupas;
}
