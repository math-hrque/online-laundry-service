package br.com.lol.lol.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lol.lol.dtos.ClienteDTO;
import br.com.lol.lol.exeptions.UsuarioJaExisteException;
import br.com.lol.lol.exeptions.UsuarioNaoExisteException;
import br.com.lol.lol.models.Cliente;
import br.com.lol.lol.services.ClienteService;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteService clienteService;

	@PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrar(@RequestBody Cliente cliente) {
        try {
            ClienteDTO clienteCadastrado = clienteService.cadastrar(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteCadastrado);
        } catch (UsuarioJaExisteException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
	
	@GetMapping("/consultar/{idUsuario}")
    public ResponseEntity<Object> consultar(@PathVariable("idUsuario") Long idUsuario) {
        try {
            ClienteDTO clienteConsultado = clienteService.consultar(idUsuario);
            return ResponseEntity.status(HttpStatus.OK).body(clienteConsultado);
        } catch (UsuarioNaoExisteException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
}
