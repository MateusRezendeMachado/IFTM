// AlunoRepository.java
package com.bookflow.repository;

import com.bookflow.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}