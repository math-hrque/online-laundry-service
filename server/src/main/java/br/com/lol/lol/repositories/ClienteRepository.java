package br.com.lol.lol.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lol.lol.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByUsuarioIdUsuario(Long idUsuario);
    Optional<Cliente> findByUsuarioEmail(String email);
    Optional<Cliente> findByCpf(String cpf);
    Optional<Cliente> findByUsuarioEmailOrCpf(String email, String cpf);
}
