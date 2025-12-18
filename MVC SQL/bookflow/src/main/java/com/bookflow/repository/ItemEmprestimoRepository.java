package com.bookflow.repository;

import com.bookflow.model.ItemEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ItemEmprestimoRepository extends JpaRepository<ItemEmprestimo, Long> {

    @Query("""
        SELECT l.titulo, COUNT(ie) 
        FROM ItemEmprestimo ie
        JOIN ie.livro l
        GROUP BY l.id, l.titulo
        ORDER BY COUNT(ie) DESC
    """)
    List<Object[]> findTopLivrosEmprestados();
}
