package br.com.lol.lol.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.lol.lol.dtos.ClienteDTO;
import br.com.lol.lol.enums.TipoPermissao;
import br.com.lol.lol.exeptions.UsuarioJaExisteException;
import br.com.lol.lol.exeptions.UsuarioNaoExisteException;
import br.com.lol.lol.models.Cliente;
import br.com.lol.lol.models.Usuario;
import br.com.lol.lol.repositories.ClienteRepository;
import br.com.lol.lol.repositories.PermissaoRepository;
import br.com.lol.lol.repositories.UsuarioRepository;
import br.com.lol.lol.utils.Generate;

@Service
public class ClienteService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PermissaoRepository permissaoRepository;

    public ClienteDTO cadastrar(@RequestBody Cliente cliente) throws UsuarioJaExisteException {
        cliente.getUsuario().setEmail(cliente.getUsuario().getEmail().toLowerCase());
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(cliente.getUsuario().getEmail());
        Optional<Cliente> clienteExistente = clienteRepository.findByCpf(cliente.getCpf());
        if (usuarioExistente.isPresent() || clienteExistente.isPresent()) {
            throw new UsuarioJaExisteException("Usuario com email e/ou CPF ja existe!");
        }

        cliente.getUsuario().setPermissao(permissaoRepository.findByTipoPermissao(TipoPermissao.CLIENTE).get());
        String senha = Generate.generatePassword();
        cliente.getUsuario().setSenha(passwordEncoder.encode(senha));
        Cliente clienteCadastrado = clienteRepository.save(cliente);
        ClienteDTO clienteCadastradoDto = mapper.map(clienteCadastrado, ClienteDTO.class);

        String mensagem = "<p>Bem vindo a Lavanderia Online!<br><br>" + 
        "Acesse a sua conta com o email cadastrado e a senha abaixo:<br><br>" +
        "<strong style='font-size: 16px;'>Sua senha: " + senha + "</strong></p>";
        emailService.enviarEmail(clienteCadastradoDto.getUsuario().getEmail(), "Cadastro - Lavanderia Online", mensagem);

        return clienteCadastradoDto;
    }

    public ClienteDTO consultar(Long idUsuario) throws UsuarioNaoExisteException {
        Optional<Cliente> clienteBD = clienteRepository.findByUsuarioIdUsuario(idUsuario);
        if (!clienteBD.isPresent()) {
            throw new UsuarioNaoExisteException("Cliente com esse idUsuario nao existe!");
        }

        ClienteDTO clienteConsultadoDto = mapper.map(clienteBD.get(), ClienteDTO.class);
        return clienteConsultadoDto;
    }

}
