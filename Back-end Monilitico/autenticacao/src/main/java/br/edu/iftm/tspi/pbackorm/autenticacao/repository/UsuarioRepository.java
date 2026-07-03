package br.edu.iftm.tspi.pbackorm.autenticacao.repository;

import br.edu.iftm.tspi.pbackorm.autenticacao.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByLogin(String login);
}
