package br.com.lol.lol.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lol.lol.models.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByUsuarioIdUsuario(Long idUsuario);
    Optional<Funcionario> findByUsuarioEmail(String email);
}
