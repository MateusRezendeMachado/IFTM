package com.bookflow.repository;

import com.bookflow.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    List<Emprestimo> findByDataDevolucaoRealIsNull();

    @Query("""
        SELECT e 
        FROM Emprestimo e 
        WHERE e.dataDevolucaoReal IS NULL 
          AND e.dataPrevistaDevolucao < CURRENT_DATE
    """)
    List<Emprestimo> findEmprestimosAtrasados();
}
