package br.com.lol.lol.controllers;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.lol.lol.dtos.ClienteDTO;
import br.com.lol.lol.dtos.ClienteFielDTO;
import br.com.lol.lol.dtos.ReceitaDTO;
import br.com.lol.lol.exeptions.DataInvalidaException;
import br.com.lol.lol.exeptions.ListaClienteVaziaException;
import br.com.lol.lol.exeptions.ListaPedidoVaziaException;
import br.com.lol.lol.services.RelatorioService;

@RestController
@RequestMapping("/relatorio")
@CrossOrigin(origins = "http://localhost:4200")
public class RelatorioController {

    private static final Logger logger = LoggerFactory.getLogger(RelatorioController.class);

    @Autowired
    RelatorioService relatorioService;

    @GetMapping("/visualizarReceitas")
    public ResponseEntity<?> visualizarReceitas(@RequestParam("dataDe") LocalDate dataDe, @RequestParam("dataAte") LocalDate dataAte) {
        try {
            List<ReceitaDTO> listaReceita = relatorioService.visualizarReceitas(dataDe, dataAte);
            return ResponseEntity.status(HttpStatus.OK).body(listaReceita);
        } catch (DataInvalidaException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (ListaPedidoVaziaException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/visualizarClientes")
    public ResponseEntity<?> visualizarClientes() {
        try {
            List<ClienteDTO> listaCliente = relatorioService.visualizarClientes();
            return ResponseEntity.status(HttpStatus.OK).body(listaCliente);
        } catch (ListaClienteVaziaException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/visualizarClientesFieis")
    public ResponseEntity<?> visualizarClientesFieis() {
        try {
            List<ClienteFielDTO> listaClienteFiel = relatorioService.visualizarClientesFieis();
            return ResponseEntity.status(HttpStatus.OK).body(listaClienteFiel);
        } catch (ListaPedidoVaziaException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
