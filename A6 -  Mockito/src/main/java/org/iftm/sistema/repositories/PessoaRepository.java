// PessoaRepository.java
package org.iftm.sistema.repositories;

import org.iftm.sistema.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    boolean existsByEmail(String email);
    List<Pessoa> findBySobrenomeContaining(String sobrenome);
}