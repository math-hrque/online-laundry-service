package br.com.lol.lol.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.lol.lol.dtos.FuncionarioDTO;
import br.com.lol.lol.enums.TipoPermissao;
import br.com.lol.lol.exeptions.FuncionarioNaoExisteException;
import br.com.lol.lol.exeptions.ListaFuncionarioVaziaException;
import br.com.lol.lol.exeptions.UsuarioJaExisteException;
import br.com.lol.lol.exeptions.UsuarioNaoExisteException;
import br.com.lol.lol.models.Funcionario;
import br.com.lol.lol.models.Usuario;
import br.com.lol.lol.repositories.FuncionarioRepository;
import br.com.lol.lol.repositories.PermissaoRepository;
import br.com.lol.lol.repositories.UsuarioRepository;

@Service
public class FuncionarioService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PermissaoRepository permissaoRepository;
    
    public FuncionarioDTO cadastrar(Funcionario funcionario) throws UsuarioJaExisteException {
        funcionario.getUsuario().setEmail(funcionario.getUsuario().getEmail().toLowerCase());
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(funcionario.getUsuario().getEmail());
        if (usuarioExistente.isPresent()) {
            throw new UsuarioJaExisteException("Ja existe outro usuario com esse email!");
        }

        funcionario.getUsuario().setPermissao(permissaoRepository.findByTipoPermissao(TipoPermissao.FUNCIONARIO).get());
        funcionario.getUsuario().setSenha(passwordEncoder.encode(funcionario.getUsuario().getSenha()));
        Funcionario funcionarioCadastrado = funcionarioRepository.save(funcionario);
        FuncionarioDTO funcionarioCadastradoDto = mapper.map(funcionarioCadastrado, FuncionarioDTO.class);
        return funcionarioCadastradoDto;
    }

    public FuncionarioDTO atualizar(Long idFuncionario, Funcionario funcionario) throws FuncionarioNaoExisteException, UsuarioJaExisteException {
        Optional<Funcionario> funcionarioBD = funcionarioRepository.findById(idFuncionario);
        if (!funcionarioBD.isPresent()) {
            throw new FuncionarioNaoExisteException("Funcionario com esse idFuncionario nao existe!");
        }

        funcionario.getUsuario().setEmail(funcionario.getUsuario().getEmail().toLowerCase());
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(funcionario.getUsuario().getEmail());
        if (usuarioExistente.isPresent() && !usuarioExistente.get().getIdUsuario().equals(funcionarioBD.get().getUsuario().getIdUsuario())) {
            throw new UsuarioJaExisteException("Ja existe outro usuario com esse email!");
        }

        funcionario.setIdFuncionario(funcionarioBD.get().getIdFuncionario());
        funcionario.getUsuario().setIdUsuario(funcionarioBD.get().getUsuario().getIdUsuario());
        funcionario.getUsuario().setPermissao(funcionarioBD.get().getUsuario().getPermissao());
        if (funcionario.getUsuario().getSenha() == null || funcionario.getUsuario().getSenha().isEmpty()) {
            funcionario.getUsuario().setSenha(funcionarioBD.get().getUsuario().getSenha());
        } else {
            funcionario.getUsuario().setSenha(passwordEncoder.encode(funcionario.getUsuario().getSenha()));
        }

        Funcionario funcionarioAtualizado = funcionarioRepository.save(funcionario);
        FuncionarioDTO funcionarioAtualizadoDto = mapper.map(funcionarioAtualizado, FuncionarioDTO.class);
        return funcionarioAtualizadoDto;
    }

    public FuncionarioDTO remover(Long idFuncionario) throws FuncionarioNaoExisteException {
        Optional<Funcionario> funcionarioBD = funcionarioRepository.findById(idFuncionario);
        if (!funcionarioBD.isPresent()) {
            throw new FuncionarioNaoExisteException("Funcionario com esse idFuncionario nao existe!");
        }

        funcionarioRepository.delete(funcionarioBD.get());
        FuncionarioDTO funcionarioConsultadoDto = mapper.map(funcionarioBD.get(), FuncionarioDTO.class);
        return funcionarioConsultadoDto;
    }

    public FuncionarioDTO consultar(Long idUsuario) throws UsuarioNaoExisteException {
        Optional<Funcionario> funcionarioBD = funcionarioRepository.findByUsuarioIdUsuario(idUsuario);
        if (!funcionarioBD.isPresent()) {
            throw new UsuarioNaoExisteException("Funcionario com esse idUsuario nao existe!");
        }

        FuncionarioDTO funcionarioConsultadoDto = mapper.map(funcionarioBD.get(), FuncionarioDTO.class);
        return funcionarioConsultadoDto;
    }

    public List<FuncionarioDTO> listar() throws ListaFuncionarioVaziaException {
        List<Funcionario> listaFuncionarioBD = funcionarioRepository.findAll();
        if (listaFuncionarioBD == null || listaFuncionarioBD.isEmpty()) {
            throw new ListaFuncionarioVaziaException("Lista de funcionarios ativos vazia!");
        }

        List<FuncionarioDTO> listaFuncionarioDto = listaFuncionarioBD.stream().map(funcionarioBD -> mapper.map(funcionarioBD, FuncionarioDTO.class)).collect(Collectors.toList());
        return listaFuncionarioDto;
    }
    
}
