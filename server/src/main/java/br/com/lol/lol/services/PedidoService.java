package br.com.lol.lol.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lol.lol.dtos.PedidoDTO;
import br.com.lol.lol.enums.TipoSituacao;
import br.com.lol.lol.exeptions.ClienteNaoExisteException;
import br.com.lol.lol.exeptions.ListaPedidoVaziaException;
import br.com.lol.lol.exeptions.PedidoNaoExisteException;
import br.com.lol.lol.exeptions.SituacaoPedidoInvalidaException;
import br.com.lol.lol.exeptions.SituacaoPedidoNaoExisteException;
import br.com.lol.lol.models.Cliente;
import br.com.lol.lol.models.Pedido;
import br.com.lol.lol.models.Situacao;
import br.com.lol.lol.repositories.ClienteRepository;
import br.com.lol.lol.repositories.PedidoRepository;
import br.com.lol.lol.repositories.SituacaoRepository;

@Service
public class PedidoService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private SituacaoRepository situacaoRepository;
    
    public PedidoDTO cadastrar(PedidoDTO pedidoDTO) throws ClienteNaoExisteException, SituacaoPedidoNaoExisteException {
        Optional<Cliente> cliente = clienteRepository.findById(pedidoDTO.getIdCliente());
        if (!cliente.isPresent()) {
            throw new ClienteNaoExisteException("Cliente nao existe!");
        }
        
        TipoSituacao tipoSituacao = pedidoDTO.getOrcamento().isAprovado() ? TipoSituacao.EM_ABERTO : TipoSituacao.REJEITADO;
        Situacao situacao = situacaoRepository.findByTipoSituacao(tipoSituacao).orElseThrow(() -> new SituacaoPedidoNaoExisteException("Situação do pedido não encontrada!"));

        Pedido pedido = mapper.map(pedidoDTO, Pedido.class);
        pedido.setIdPedido(0L);
        pedido.setSituacao(situacao);

        Optional<Long> ultimoNumeroPedido = pedidoRepository.findMaxNumeroPedido();
        if (ultimoNumeroPedido.isPresent()) {
            pedido.setNumeroPedido(ultimoNumeroPedido.get() + 1L);
        } else {
            pedido.setNumeroPedido(10000L);
        }

        Pedido pedidoCadastrado = pedidoRepository.save(pedido);
        PedidoDTO pedidoCadastradoDto = mapper.map(pedidoCadastrado, PedidoDTO.class);
        return pedidoCadastradoDto;
    }

    public PedidoDTO atualizarPorCliente(Long numeroPedido, PedidoDTO pedidoDTO) throws PedidoNaoExisteException, SituacaoPedidoInvalidaException, SituacaoPedidoNaoExisteException {
        Optional<Pedido> pedidoBD = pedidoRepository.findByNumeroPedido(numeroPedido);
        if (!pedidoBD.isPresent()) {
            throw new PedidoNaoExisteException("Pedido nao existe com esse numeroPedido!");
        }

        switch (pedidoDTO.getSituacao()) {
            case CANCELADO:
                if (pedidoBD.get().getSituacao().getTipoSituacao().equals(TipoSituacao.EM_ABERTO)) {
                    Situacao situacao = situacaoRepository.findByTipoSituacao(TipoSituacao.CANCELADO).orElseThrow(() -> new SituacaoPedidoNaoExisteException("Situação do pedido não encontrada!"));
                    pedidoBD.get().setSituacao(situacao);
                    break;
                }
                throw new SituacaoPedidoInvalidaException("Mudanca de situacao do pedido nao permitida!");
            case PAGO:
                if (pedidoBD.get().getSituacao().getTipoSituacao().equals(TipoSituacao.AGUARDANDO_PAGAMENTO)) {
                    Situacao situacao = situacaoRepository.findByTipoSituacao(TipoSituacao.PAGO).orElseThrow(() -> new SituacaoPedidoNaoExisteException("Situação do pedido não encontrada!"));
                    pedidoBD.get().setSituacao(situacao);
                    pedidoBD.get().pagar();
                    break;
                }
                throw new SituacaoPedidoInvalidaException("Mudanca de situacao do pedido nao permitida!");
            default:
                throw new SituacaoPedidoInvalidaException("Situacao do pedido invalida!");
        }

        Pedido pedidoAtualizado = pedidoRepository.save(pedidoBD.get());
        PedidoDTO pedidoAtualizadoDto = mapper.map(pedidoAtualizado, PedidoDTO.class);
        return pedidoAtualizadoDto;
    }

    public PedidoDTO atualizarPorFuncionario(Long numeroPedido, PedidoDTO pedidoDTO) throws PedidoNaoExisteException, SituacaoPedidoInvalidaException, SituacaoPedidoNaoExisteException {
        Optional<Pedido> pedidoBD = pedidoRepository.findByNumeroPedido(numeroPedido);
        if (!pedidoBD.isPresent()) {
            throw new PedidoNaoExisteException("Pedido nao existe com esse numeroPedido!");
        }

        switch (pedidoDTO.getSituacao()) {
            case RECOLHIDO:
                if (pedidoBD.get().getSituacao().getTipoSituacao().equals(TipoSituacao.EM_ABERTO)) {
                    Situacao situacao = situacaoRepository.findByTipoSituacao(TipoSituacao.RECOLHIDO).orElseThrow(() -> new SituacaoPedidoNaoExisteException("Situação do pedido não encontrada!"));
                    pedidoBD.get().setSituacao(situacao);
                    break;
                }
                throw new SituacaoPedidoInvalidaException("Mudanca de situacao do pedido nao permitida!");
            case AGUARDANDO_PAGAMENTO:
                if (pedidoBD.get().getSituacao().getTipoSituacao().equals(TipoSituacao.RECOLHIDO)) {
                    Situacao situacao = situacaoRepository.findByTipoSituacao(TipoSituacao.AGUARDANDO_PAGAMENTO).orElseThrow(() -> new SituacaoPedidoNaoExisteException("Situação do pedido não encontrada!"));
                    pedidoBD.get().setSituacao(situacao);
                    break;
                }
                throw new SituacaoPedidoInvalidaException("Mudanca de situacao do pedido nao permitida!");
            case FINALIZADO:
                if (pedidoBD.get().getSituacao().getTipoSituacao().equals(TipoSituacao.PAGO)) {
                    Situacao situacao = situacaoRepository.findByTipoSituacao(TipoSituacao.FINALIZADO).orElseThrow(() -> new SituacaoPedidoNaoExisteException("Situação do pedido não encontrada!"));
                    pedidoBD.get().setSituacao(situacao);
                    break;
                }
                throw new SituacaoPedidoInvalidaException("Mudanca de situacao do pedido nao permitida!");
            default:
                throw new SituacaoPedidoInvalidaException("Situacao do pedido invalida!");
        }

        Pedido pedidoAtualizado = pedidoRepository.save(pedidoBD.get());
        PedidoDTO pedidoAtualizadoDto = mapper.map(pedidoAtualizado, PedidoDTO.class);
        return pedidoAtualizadoDto;
    }

    public PedidoDTO consultar(Long numeroPedido, Long idCliente) throws PedidoNaoExisteException {
        Optional<Pedido> pedidoBD = pedidoRepository.findByNumeroPedidoAndClienteIdCliente(numeroPedido, idCliente);
        if (!pedidoBD.isPresent()) {
            throw new PedidoNaoExisteException("Pedido nao existe para esse numeroPedido e cliente!");
        }

        PedidoDTO pedidoConsultadoDto = mapper.map(pedidoBD.get(), PedidoDTO.class);
        return pedidoConsultadoDto;
    }

    public List<PedidoDTO> listar() throws ListaPedidoVaziaException {
        List<Pedido> listaPedidoBD = pedidoRepository.findAll();
        if (listaPedidoBD.isEmpty() || listaPedidoBD.isEmpty()) {
            throw new ListaPedidoVaziaException("Lista de pedidos vazia!");
        }

        List<PedidoDTO> listaPedidoDto = listaPedidoBD.stream().map(pedidoBD -> mapper.map(pedidoBD, PedidoDTO.class)).collect(Collectors.toList());
        return listaPedidoDto;
    }

    public List<PedidoDTO> listarPorIdUsuario(Long idUsuario) throws ListaPedidoVaziaException {
        Optional<List<Pedido>> listaPedidoBD = pedidoRepository.findByClienteUsuarioIdUsuario(idUsuario);
        if (!listaPedidoBD.isPresent() || listaPedidoBD.get().isEmpty()) {
            throw new ListaPedidoVaziaException("Lista de pedidos vazia!");
        }
        
        List<PedidoDTO> listaPedidoDto = listaPedidoBD.get().stream().map(pedidoBD -> mapper.map(pedidoBD, PedidoDTO.class)).collect(Collectors.toList());
        return listaPedidoDto;
    }

    public List<PedidoDTO> listarPorIdCliente(Long idCliente) throws ListaPedidoVaziaException {
        Optional<List<Pedido>> listaPedidoBD = pedidoRepository.findByClienteIdCliente(idCliente);
        if (!listaPedidoBD.isPresent() || listaPedidoBD.get().isEmpty()) {
            throw new ListaPedidoVaziaException("Lista de pedidos vazia!");
        }

        List<PedidoDTO> listaPedidoDto = listaPedidoBD.get().stream().map(pedidoBD -> mapper.map(pedidoBD, PedidoDTO.class)).collect(Collectors.toList());
        return listaPedidoDto;
    }
    
}
