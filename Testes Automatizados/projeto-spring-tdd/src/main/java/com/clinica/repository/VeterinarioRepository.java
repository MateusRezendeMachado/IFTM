package com.clinica.repository;

import com.clinica.model.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {

    // Ciclo 1 - busca exata e case insensitive
    Optional<Veterinario> findByNome(String nome);
    Optional<Veterinario> findByNomeIgnoreCase(String nome);

    // Ciclo 2 - busca por parte do nome (LIKE)
   List<Veterinario> findByNomeContainingIgnoreCase(String termo);

    // Ciclo 3 - filtros de salário
    List<Veterinario> findBySalarioGreaterThan(Double salario);
    List<Veterinario> findBySalarioLessThan(Double salario);
    List<Veterinario> findBySalarioBetween(Double min, Double max);

    // Ciclo 4 - filtro por data de nascimento (entre)
    List<Veterinario> findByDataNascimentoBetween(Instant inicio, Instant fim);

    // Ciclo 6 - contagem acima de teto salarial
    long countBySalarioGreaterThan(Double teto);
}