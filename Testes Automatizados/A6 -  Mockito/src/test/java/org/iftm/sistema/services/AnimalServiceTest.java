package org.iftm.sistema.services;

import org.iftm.sistema.entities.Animal;
import org.iftm.sistema.repositories.AnimalRepository;
import org.iftm.sistema.services.AnimalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AnimalServiceTest {

    @Mock
    private AnimalRepository repository;

    @InjectMocks
    private AnimalService service;

    private Animal animalValido;

    @BeforeEach
    void setUp() {
        animalValido = new Animal(null, "Rex", "Cachorro", 3, false);
    }

    // ========== DESAFIO 1: TESTAR BUSCAS POR ESPÉCIE ==========
    @Test
    void deveBuscarAnimaisPorEspecie() {
        // Arrange
        String especie = "Cachorro";
        Animal a1 = new Animal(1L, "Rex", "Cachorro", 3, true);
        Animal a2 = new Animal(2L, "Buddy", "Cachorro", 2, true);
        List<Animal> listaMock = Arrays.asList(a1, a2);

        when(repository.findByEspecie(especie)).thenReturn(listaMock);

        // Act
        List<Animal> resultado = service.buscarPorEspecie(especie);

        // Assert
        assertEquals(2, resultado.size());
        verify(repository).findByEspecie(especie);
    }

    // ========== DESAFIO 2: PROTEGER EXCLUSÃO ==========
    @Test
    void deveLancarExcecaoAoApagarAnimalQuandoIdNaoExistir() {
        Long idInexistente = 999L;
        when(repository.existsById(idInexistente)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.deletarPorId(idInexistente);
        });

        assertEquals("Animal não encontrado", exception.getMessage());
        verify(repository, never()).deleteById(any());
    }

    // ========== CICLO 1: STATUS PADRÃO (INTERNADO = TRUE) ==========
    @Test
    void deveCadastrarAnimalComInternadoTrue() {
        Animal animalSalvo = new Animal(1L, "Rex", "Cachorro", 3, true);
        when(repository.save(any(Animal.class))).thenReturn(animalSalvo);

        Animal resultado = service.cadastrar(animalValido);

        assertTrue(resultado.isInternado());
        verify(repository).save(any(Animal.class));
    }

    // ========== CICLO 2: VALIDAÇÃO DE ESPÉCIE ==========
    @Test
    void deveLancarExcecaoAoCadastrarAnimalComEspecieInvalida() {
        Animal animalInvalido = new Animal(null, "Rex", "Gato Selvagem", 3, false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.cadastrar(animalInvalido);
        });

        assertEquals("Espécie não atendida pela clínica", exception.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    void deveAceitarAnimalComEspecieValida() {
        when(repository.save(any(Animal.class))).thenReturn(animalValido);

        Animal resultado = service.cadastrar(animalValido);

        assertNotNull(resultado);
        verify(repository).save(any(Animal.class));
    }

    // ========== CICLO 3: AÇÃO ESPECÍFICA (DAR ALTA) ==========
    @Test
    void deveDarAltaComSucesso() {
        Long id = 1L;
        Animal animalInternado = new Animal(id, "Rex", "Cachorro", 3, true);
        when(repository.findById(id)).thenReturn(Optional.of(animalInternado));
        when(repository.save(any(Animal.class))).thenReturn(animalInternado);

        service.darAlta(id);

        assertFalse(animalInternado.isInternado());
        verify(repository).save(animalInternado);
    }

    @Test
    void deveLancarExcecaoAoDarAltaAnimalInexistente() {
        Long id = 999L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.darAlta(id);
        });

        assertEquals("Animal não encontrado", exception.getMessage());
        verify(repository, never()).save(any());
    }
}