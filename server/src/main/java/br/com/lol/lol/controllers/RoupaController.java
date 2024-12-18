package br.com.lol.lol.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lol.lol.dtos.RoupaDTO;
import br.com.lol.lol.exeptions.ListaRoupaVaziaException;
import br.com.lol.lol.exeptions.RoupaNaoExisteException;
import br.com.lol.lol.services.RoupaService;

@RestController
@RequestMapping("/roupa")
@CrossOrigin(origins = "http://localhost:4200")
public class RoupaController {

    private static final Logger logger = LoggerFactory.getLogger(RoupaController.class);

    @Autowired
    private RoupaService roupaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrar(@RequestBody RoupaDTO roupaDTO) {
        try {
            RoupaDTO roupaCadastrada = roupaService.cadastrar(roupaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(roupaCadastrada);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{idRoupa}")
    public ResponseEntity<Object> atualizar(@PathVariable("idRoupa") Long idRoupa, @RequestBody RoupaDTO roupaDTO) {
        try {
            RoupaDTO roupaAtualizada = roupaService.atualizar(idRoupa, roupaDTO);
            return ResponseEntity.status(HttpStatus.OK).body(roupaAtualizada);
        } catch (RoupaNaoExisteException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/inativar/{idRoupa}")
    public ResponseEntity<Object> inativar(@PathVariable("idRoupa") Long idRoupa) {
        try {
            RoupaDTO roupaInativada = roupaService.inativar(idRoupa);
            return ResponseEntity.status(HttpStatus.OK).body(roupaInativada);
        } catch (RoupaNaoExisteException e) {
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
            List<RoupaDTO> listaRoupa = roupaService.listar();
            return ResponseEntity.status(HttpStatus.OK).body(listaRoupa);
        } catch (ListaRoupaVaziaException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
