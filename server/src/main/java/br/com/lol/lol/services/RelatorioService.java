package br.com.lol.lol.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lol.lol.dtos.ClienteDTO;
import br.com.lol.lol.dtos.ClienteFielDTO;
import br.com.lol.lol.dtos.ReceitaDTO;
import br.com.lol.lol.exeptions.DataInvalidaException;
import br.com.lol.lol.exeptions.ListaClienteVaziaException;
import br.com.lol.lol.exeptions.ListaPedidoVaziaException;
import br.com.lol.lol.models.Cliente;
import br.com.lol.lol.models.Pedido;
import br.com.lol.lol.repositories.ClienteRepository;
import br.com.lol.lol.repositories.PedidoRepository;

@Service
public class RelatorioService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;
    
    public List<ReceitaDTO> visualizarReceitas(LocalDate dataDe, LocalDate dataAte) throws DataInvalidaException, ListaPedidoVaziaException {
        if (dataAte.isBefore(dataDe)) {
            throw new DataInvalidaException("Datas invalidas!");
        }

        ZoneId zoneId = ZoneId.systemDefault();
        OffsetDateTime dataDeOffset = dataDe.atStartOfDay(zoneId).toOffsetDateTime();
        OffsetDateTime dataAteOffset = dataAte.atTime(LocalTime.MAX).atOffset(ZoneOffset.from(zoneId.getRules().getOffset(LocalDateTime.now())));
        Optional<List<Pedido>> listaPedidoBD = pedidoRepository.findByDataPagamentoBetween(dataDeOffset, dataAteOffset);

        if (!listaPedidoBD.isPresent()) {
            throw new ListaPedidoVaziaException("Lista de pedidos vazia!");
        }

        Map<LocalDate, Double> receitasMap = listaPedidoBD.get().stream().collect(Collectors.groupingBy(pedido -> 
            pedido.getDataPagamento().toLocalDate(),Collectors.summingDouble(pedido -> pedido.getOrcamento().getValor())
        ));

        List<LocalDate> listaData = Stream.iterate(dataDe, data -> !data.isAfter(dataAte), data -> data.plusDays(1)).collect(Collectors.toList());
        Map<LocalDate, Double> periodoMap = listaData.stream().collect(Collectors.toMap(data -> data,data -> receitasMap.getOrDefault(data, 0.0)));

        List<ReceitaDTO> listaReceitaDTO = periodoMap.entrySet().stream().map(entry -> {
            ReceitaDTO receitaDTO = new ReceitaDTO();
            receitaDTO.setData(entry.getKey());
            receitaDTO.setReceita(entry.getValue());
            return receitaDTO;
        }).sorted(Comparator.comparing(ReceitaDTO::getData)).collect(Collectors.toList());

        return listaReceitaDTO;
    }

    public List<ClienteDTO> visualizarClientes() throws ListaClienteVaziaException {
        List<Cliente> listaClienteBD = clienteRepository.findAll();
        if (listaClienteBD.isEmpty()) {
            throw new ListaClienteVaziaException("Lista de clientes vazia!");
        }

        List<ClienteDTO> listaClienteDTO = listaClienteBD.stream().map(clienteBD -> mapper.map(clienteBD, ClienteDTO.class)).collect(Collectors.toList());
        return listaClienteDTO;
    }

    public List<ClienteFielDTO> visualizarClientesFieis() throws ListaPedidoVaziaException {
        List<Pedido> listaPedidoBD = pedidoRepository.findAll();
        if (listaPedidoBD.isEmpty()) {
            throw new ListaPedidoVaziaException("Lista de pedidos vazia!");
        }
            
        List<Pedido> listaPedidosPagosMap = listaPedidoBD.stream().filter(pedido -> pedido.getDataPagamento() != null).collect(Collectors.toList());
        Map<Cliente, List<Pedido>> clienteListaPedidosPagosMap = listaPedidosPagosMap.stream().collect(Collectors.groupingBy(Pedido::getCliente));
        
        List<ClienteFielDTO> listaClienteFiel = clienteListaPedidosPagosMap.entrySet().stream().map(entry -> {
            Cliente cliente = entry.getKey();
            List<Pedido> pedidos = entry.getValue();
            int quantidadePedidos = pedidos.size();
            Double receita = pedidos.stream().mapToDouble(pedido -> pedido.getOrcamento().getValor()).sum();
            ClienteDTO clienteDTO = mapper.map(cliente, ClienteDTO.class);
            ClienteFielDTO clienteFielDTO = new ClienteFielDTO();
            clienteFielDTO.setCliente(clienteDTO);
            clienteFielDTO.setQuantidadePedidos(quantidadePedidos);
            clienteFielDTO.setReceita(receita);
            return clienteFielDTO;
        }).sorted(Comparator.comparingInt(ClienteFielDTO::getQuantidadePedidos).reversed()).limit(3).collect(Collectors.toList());

        return listaClienteFiel;
    }

}
