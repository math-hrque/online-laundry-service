package br.com.lol.lol.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lol.lol.dtos.UsuarioRequestDTO;
import br.com.lol.lol.dtos.UsuarioResponseDTO;
import br.com.lol.lol.exeptions.UsuarioNaoExisteException;
import br.com.lol.lol.services.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO) {
        try {
            UsuarioResponseDTO UsuarioResponseDTO = usuarioService.login(usuarioRequestDTO);
            return ResponseEntity.status(HttpStatus.OK).body(UsuarioResponseDTO);
        } catch (UsuarioNaoExisteException | BadCredentialsException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
