// ProdutoRepository.java
package org.iftm.sistema.repositories;

import org.iftm.sistema.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}