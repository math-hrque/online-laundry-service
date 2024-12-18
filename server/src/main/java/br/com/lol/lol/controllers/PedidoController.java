package br.com.lol.lol.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.lol.lol.dtos.PedidoDTO;
import br.com.lol.lol.exeptions.ClienteNaoExisteException;
import br.com.lol.lol.exeptions.ListaPedidoVaziaException;
import br.com.lol.lol.exeptions.PedidoNaoExisteException;
import br.com.lol.lol.exeptions.SituacaoPedidoInvalidaException;
import br.com.lol.lol.services.PedidoService;

@RestController
@RequestMapping("/pedido")
@CrossOrigin(origins = "http://localhost:4200")
public class PedidoController {

    private static final Logger logger = LoggerFactory.getLogger(PedidoController.class);

    @Autowired
    PedidoService pedidoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrar(@RequestBody PedidoDTO pedidoDTO) {
        try {
            PedidoDTO pedidoCadastrado = pedidoService.cadastrar(pedidoDTO);
            return ResponseEntity.status(HttpStatus.OK).body(pedidoCadastrado);
        } catch (ClienteNaoExisteException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/atualizarPorCliente/{numeroPedido}")
    public ResponseEntity<Object> atualizarPorCliente(@PathVariable("numeroPedido") Long numeroPedido, @RequestBody PedidoDTO pedidoDTO) {
        try {
            PedidoDTO pedidoAtualizado = pedidoService.atualizarPorCliente(numeroPedido, pedidoDTO);
            return ResponseEntity.status(HttpStatus.OK).body(pedidoAtualizado);
        } catch (PedidoNaoExisteException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (SituacaoPedidoInvalidaException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/atualizarPorFuncionario/{numeroPedido}")
    public ResponseEntity<Object> atualizarPorFuncionario(@PathVariable("numeroPedido") Long numeroPedido, @RequestBody PedidoDTO pedidoDTO) {
        try {
            PedidoDTO pedidoAtualizado = pedidoService.atualizarPorFuncionario(numeroPedido, pedidoDTO);
            return ResponseEntity.status(HttpStatus.OK).body(pedidoAtualizado);
        } catch (PedidoNaoExisteException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (SituacaoPedidoInvalidaException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/consultar")
    public ResponseEntity<Object> consultar(@RequestParam("numeroPedido") Long numeroPedido, @RequestParam("idCliente") Long idCliente) {
        try {
            PedidoDTO pedidoConsultado = pedidoService.consultar(numeroPedido, idCliente);
            return ResponseEntity.status(HttpStatus.OK).body(pedidoConsultado);
        } catch (PedidoNaoExisteException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        try {
            List<PedidoDTO> listaPedido = pedidoService.listar();
            return ResponseEntity.status(HttpStatus.OK).body(listaPedido);
        } catch (ListaPedidoVaziaException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/listarPorIdUsuario/{idUsuario}")
    public ResponseEntity<?> listarPorIdUsuario(@PathVariable("idUsuario") Long idUsuario) {
        try {
            List<PedidoDTO> listaPedido = pedidoService.listarPorIdUsuario(idUsuario);
            return ResponseEntity.status(HttpStatus.OK).body(listaPedido);
        } catch (ListaPedidoVaziaException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/listarPorIdCliente/{idCliente}")
    public ResponseEntity<?> listarPorIdCliente(@PathVariable("idCliente") Long idCliente) {
        try {
            List<PedidoDTO> listaPedido = pedidoService.listarPorIdCliente(idCliente);
            return ResponseEntity.status(HttpStatus.OK).body(listaPedido);
        } catch (ListaPedidoVaziaException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
