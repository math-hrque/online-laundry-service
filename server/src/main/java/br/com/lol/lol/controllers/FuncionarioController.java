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

import br.com.lol.lol.dtos.FuncionarioDTO;
import br.com.lol.lol.exeptions.FuncionarioNaoExisteException;
import br.com.lol.lol.exeptions.ListaFuncionarioVaziaException;
import br.com.lol.lol.exeptions.UsuarioJaExisteException;
import br.com.lol.lol.exeptions.UsuarioNaoExisteException;
import br.com.lol.lol.models.Funcionario;
import br.com.lol.lol.services.FuncionarioService;

@RestController
@RequestMapping("/funcionario")
@CrossOrigin(origins = "http://localhost:4200")
public class FuncionarioController {

    private static final Logger logger = LoggerFactory.getLogger(FuncionarioController.class);

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrar(@RequestBody Funcionario funcionario) {
        try {
            FuncionarioDTO funcionarioCadastrado = funcionarioService.cadastrar(funcionario);
            return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioCadastrado);
        } catch (UsuarioJaExisteException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{idFuncionario}")
    public ResponseEntity<Object> atualizar(@PathVariable("idFuncionario") Long idFuncionario, @RequestBody Funcionario funcionario) {
        try {
            FuncionarioDTO funcionarioAtualizado = funcionarioService.atualizar(idFuncionario, funcionario);
            return ResponseEntity.status(HttpStatus.OK).body(funcionarioAtualizado);
        } catch (FuncionarioNaoExisteException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (UsuarioJaExisteException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/remover/{idFuncionario}")
    public ResponseEntity<Object> remover(@PathVariable("idFuncionario") Long idFuncionario) {
        try {
            FuncionarioDTO funcionarioRemovido = funcionarioService.remover(idFuncionario);
            return ResponseEntity.status(HttpStatus.OK).body(funcionarioRemovido);
        } catch (FuncionarioNaoExisteException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/consultar/{idUsuario}")
    public ResponseEntity<Object> consultar(@PathVariable("idUsuario") Long idUsuario) {
        try {
            Object funcionarioConsultado = funcionarioService.consultar(idUsuario);
            return ResponseEntity.status(HttpStatus.OK).body(funcionarioConsultado);
        } catch (UsuarioNaoExisteException e) {
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
            List<FuncionarioDTO> listaFuncionario = funcionarioService.listar();
            return ResponseEntity.status(HttpStatus.OK).body(listaFuncionario);
        } catch (ListaFuncionarioVaziaException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
}
