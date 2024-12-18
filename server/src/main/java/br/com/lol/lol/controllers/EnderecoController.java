package br.com.lol.lol.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lol.lol.dtos.EnderecoDTO;
import br.com.lol.lol.exeptions.CepInvalidoException;
import br.com.lol.lol.exeptions.CepNaoExisteException;
import br.com.lol.lol.services.EnderecoService;

@RestController
@RequestMapping("/endereco")
@CrossOrigin(origins = "http://localhost:4200")
public class EnderecoController {

    private static final Logger logger = LoggerFactory.getLogger(EnderecoController.class);

    @Autowired
    EnderecoService enderecoService;

    @GetMapping("/consultar/{cep}")
    public ResponseEntity<Object> consultar(@PathVariable("cep") String cep) {
        try {
            EnderecoDTO enderecoConsultado  = enderecoService.consultar(cep);
            return ResponseEntity.status(HttpStatus.OK).body(enderecoConsultado);
        } catch(CepNaoExisteException | CepInvalidoException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch(Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
