package com.clinica.repository;

import com.clinica.model.Veterinario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@Sql(scripts = "/import.sql")
class VeterinarioRepositoryTests {

    @Autowired
    private VeterinarioRepository repository;

    @Test
    @DisplayName("C1-T1: Busca exata com letras minúsculas quando banco tem MAIÚSCULAS -> deve retornar vazio")
    void testFindByNomeExact_UpperCaseStored_LowercaseSearch_ShouldFail() {
        Optional<Veterinario> result = repository.findByNome("pedro");
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("C1-T2: Busca exata com letras maiúsculas correspondentes -> encontra veterinário")
    void testFindByNomeExact_ExactMatchUpperCase_ShouldReturn() {
        Optional<Veterinario> result = repository.findByNome("PEDRO");
        assertThat(result).isPresent();
        assertThat(result.get().getEmail()).isEqualTo("pedro@clinica.com");
    }

    @Test
    @DisplayName("C1-T3: Busca case insensitive com nome inexistente -> retorna vazio")
    void testFindByNomeIgnoreCase_NonExistentName_ShouldReturnEmpty() {
        Optional<Veterinario> result = repository.findByNomeIgnoreCase("jose");
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("C1-T4: Busca case insensitive com nome existente em caixa diferente -> retorna veterinário")
    void testFindByNomeIgnoreCase_ExistingNameDifferentCase_ShouldReturn() {
        Optional<Veterinario> result = repository.findByNomeIgnoreCase("joao");
        assertThat(result).isPresent();
        assertThat(result.get().getEmail()).isEqualTo("joao@clinica.com");
    }

    @Test
    @DisplayName("C2-T1: Busca por 'ro' deve retornar PEDRO e ROBERTO")
    void testFindByNomeContaining_Ro_ShouldReturnPedroAndRoberto() {
        List<Veterinario> result = repository.findByNomeContainingIgnoreCase("ro");
        assertThat(result)
                .extracting(Veterinario::getNome)
                .containsExactlyInAnyOrder("PEDRO", "ROBERTO");
    }

    @Test
    @DisplayName("C2-T2: String vazia retorna todos os veterinários")
    void testFindByNomeContaining_EmptyString_ShouldReturnAll() {
        List<Veterinario> result = repository.findByNomeContainingIgnoreCase("");
        assertThat(result).hasSize(5);
    }

    @Test
    @DisplayName("C3-A: Salário superior a 5000")
    void testFindBySalarioGreaterThan_Above5000_ShouldReturnFiltered() {
        List<Veterinario> result = repository.findBySalarioGreaterThan(5000.0);
        assertThat(result)
                .extracting(Veterinario::getSalario)
                .allMatch(s -> s > 5000.0);
        assertThat(result).hasSize(3);
    }

    @Test
    @DisplayName("C3-B: Salário inferior a 4000")
    void testFindBySalarioLessThan_Below4000_ShouldReturnFiltered() {
        List<Veterinario> result = repository.findBySalarioLessThan(4000.0);
        assertThat(result)
                .extracting(Veterinario::getSalario)
                .allMatch(s -> s < 4000.0);
        assertThat(result).hasSize(2);
    }

    @Test
    @DisplayName("C3-C: Salário entre 3000 e 7000")
    void testFindBySalarioBetween_3000And7000_ShouldReturnFiltered() {
        List<Veterinario> result = repository.findBySalarioBetween(3000.0, 7000.0);
        assertThat(result)
                .extracting(Veterinario::getSalario)
                .allMatch(s -> s >= 3000.0 && s <= 7000.0);
        assertThat(result).hasSize(2);
    }

    @Test
    @DisplayName("C4: Veterinários nascidos entre 1985 e 1995")
    void testFindByDataNascimentoBetween_1985And1995_ShouldReturnCorrect() {
        Instant inicio = Instant.parse("1985-01-01T00:00:00Z");
        Instant fim = Instant.parse("1995-12-31T23:59:59Z");

        List<Veterinario> result = repository.findByDataNascimentoBetween(inicio, fim);

        assertThat(result)
                .extracting(Veterinario::getEmail)
                .containsExactlyInAnyOrder(
                        "pedro@clinica.com",
                        "joao@clinica.com",
                        "ana@clinica.com"
                );
    }

    @Test
    @DisplayName("C5: Atualizar veterinário e verificar que só é encontrado pelos novos dados")
    void testUpdate_ShouldPersistAndOldDataNoLongerMatch() {
        Veterinario pedro = repository.findByNome("PEDRO").orElseThrow();

        pedro.setNome("PEDRO ALMEIDA");
        pedro.setSalario(9999.0);
        pedro.setDataNascimento(Instant.parse("1980-01-01T00:00:00Z"));

        repository.save(pedro);
        repository.flush();

        assertThat(repository.findByNome("PEDRO")).isEmpty();
        assertThat(repository.findByNome("PEDRO ALMEIDA")).isPresent();

        assertThat(repository.findBySalarioGreaterThan(9000.0))
                .extracting(Veterinario::getNome)
                .contains("PEDRO ALMEIDA");

        assertThat(repository.findBySalarioBetween(9000.0, 10000.0))
                .extracting(Veterinario::getNome)
                .contains("PEDRO ALMEIDA");

        assertThat(repository.findByDataNascimentoBetween(
                Instant.parse("1979-01-01T00:00:00Z"),
                Instant.parse("1981-01-01T00:00:00Z")
        )).extracting(Veterinario::getNome)
          .contains("PEDRO ALMEIDA");
    }

    @Test
    @DisplayName("C6: Contar veterinários com salário > 5000")
    void testCountBySalarioGreaterThan_Above5000_ShouldReturnCorrectCount() {
        long count = repository.countBySalarioGreaterThan(5000.0);
        assertEquals(3, count);
    }

    @Test
    @DisplayName("C7: Tentar inserir veterinário com e-mail já existente lança DataIntegrityViolationException")
    void testUniqueEmailConstraint_ShouldThrowException() {
        Veterinario existing = repository.findByNome("PEDRO").orElseThrow();

        Veterinario newVet = new Veterinario(
                "Fulano",
                4000.0,
                Instant.now(),
                existing.getEmail()
        );

        assertThrows(DataIntegrityViolationException.class, () -> {
            repository.save(newVet);
            repository.flush();
        });
    }
}