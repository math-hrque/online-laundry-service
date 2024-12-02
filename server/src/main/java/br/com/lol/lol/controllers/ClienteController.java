package br.com.lol.lol.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lol.lol.dtos.ClienteDTO;
import br.com.lol.lol.models.Cliente;
import br.com.lol.lol.services.ClienteService;

@CrossOrigin
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

	@PostMapping("/cadastrar")
    public ResponseEntity<ClienteDTO> cadastrar(@RequestBody Cliente cliente) {
		  return clienteService.cadastrar(cliente);
    }
	
	@GetMapping("/consultar/{idUsuario}")
    public ResponseEntity<ClienteDTO> consultar(@PathVariable("idUsuario") Long idUsuario) {
		  return clienteService.consultar(idUsuario);
    }
    
}
