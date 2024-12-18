package br.com.lol.lol.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.lol.lol.dtos.UsuarioRequestDTO;
import br.com.lol.lol.dtos.UsuarioResponseDTO;
import br.com.lol.lol.exeptions.UsuarioNaoExisteException;
import br.com.lol.lol.models.Usuario;
import br.com.lol.lol.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public UsuarioResponseDTO login(UsuarioRequestDTO usuarioRequestDTO) throws UsuarioNaoExisteException, BadCredentialsException {
        usuarioRequestDTO.setEmail(usuarioRequestDTO.getEmail().toLowerCase());
        Optional<Usuario> usuarioBD = usuarioRepository.findByEmail(usuarioRequestDTO.getEmail());

        if (!usuarioBD.isPresent()) {
            throw new UsuarioNaoExisteException("Usuario nao existe!");
        }

        if (!passwordEncoder.matches(usuarioRequestDTO.getSenha(), usuarioBD.get().getSenha())) {
            throw new BadCredentialsException("Senha invalida!");
        }

        UsuarioResponseDTO usuarioResponseDTO = mapper.map(usuarioBD.get(), UsuarioResponseDTO.class);
        return usuarioResponseDTO;
    }

}
