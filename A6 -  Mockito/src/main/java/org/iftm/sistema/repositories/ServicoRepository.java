// ServicoRepository.java
package org.iftm.sistema.repositories;

import org.iftm.sistema.entities.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}