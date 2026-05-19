package org.iftm.sistema.services;

import org.iftm.sistema.entities.Pessoa;
import org.iftm.sistema.repositories.PessoaRepository;
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
public class PessoaServiceTest {

    @Mock
    private PessoaRepository repository;

    @InjectMocks
    private PessoaService service;

    private Pessoa pessoaValida;

    @BeforeEach
    void setUp() {
        pessoaValida = new Pessoa(null, "João", "Silva", "joao@email.com", false);
    }

    // ========== DESAFIO 1: TESTAR BUSCAS COM LISTAS ==========
    @Test
    void deveBuscarPessoasPorParteDoNome() {
        // Arrange
        String parteNome = "Silva";
        Pessoa p1 = new Pessoa(1L, "João", "Silva", "joao@email.com", true);
        Pessoa p2 = new Pessoa(2L, "Maria", "Silva", "maria@email.com", true);
        List<Pessoa> listaMock = Arrays.asList(p1, p2);

        when(repository.findBySobrenomeContaining(parteNome)).thenReturn(listaMock);

        // Act
        List<Pessoa> resultado = service.buscarPorSobrenome(parteNome);

        // Assert
        assertEquals(2, resultado.size());
        verify(repository).findBySobrenomeContaining(parteNome);
    }

    // ========== DESAFIO 2: PROTEGER EXCLUSÃO ==========
    @Test
    void deveLancarExcecaoAoApagarQuandoIdNaoExistir() {
        // Arrange
        Long idInexistente = 999L;
        when(repository.existsById(idInexistente)).thenReturn(false);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.deletarPorId(idInexistente);
        });

        assertEquals("Pessoa não encontrada", exception.getMessage());
        verify(repository, never()).deleteById(any());
    }

    @Test
    void deveApagarQuandoIdExistir() {
        // Arrange
        Long idExistente = 1L;
        when(repository.existsById(idExistente)).thenReturn(true);

        // Act
        service.deletarPorId(idExistente);

        // Assert
        verify(repository).deleteById(idExistente);
    }

    // ========== CICLO 1: STATUS PADRÃO ==========
    @Test
    void deveCadastrarPessoaComStatusAtivoTrue() {
        // Arrange
        Pessoa pessoaSalva = new Pessoa(1L, "João", "Silva", "joao@email.com", true);
        when(repository.save(any(Pessoa.class))).thenReturn(pessoaSalva);
        when(repository.existsByEmail(anyString())).thenReturn(false);

        // Act
        Pessoa resultado = service.salvar(pessoaValida);

        // Assert
        assertTrue(resultado.isAtivo());
        verify(repository).save(any(Pessoa.class));
    }

    // ========== CICLO 2: VALIDAÇÃO (EMAIL DUPLICADO) ==========
    @Test
    void deveLancarExcecaoAoSalvarQuandoEmailJaExistir() {
        // Arrange
        when(repository.existsByEmail("joao@email.com")).thenReturn(true);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.salvar(pessoaValida);
        });

        assertEquals("E-mail já cadastrado!", exception.getMessage());
        verify(repository, never()).save(any());
    }

    // ========== CICLO 3: AÇÃO ESPECÍFICA (INATIVAR) ==========
    @Test
    void deveInativarPessoaComSucesso() {
        // Arrange
        Long id = 1L;
        Pessoa pessoaAtiva = new Pessoa(id, "João", "Silva", "joao@email.com", true);
        when(repository.findById(id)).thenReturn(Optional.of(pessoaAtiva));
        when(repository.save(any(Pessoa.class))).thenReturn(pessoaAtiva);

        // Act
        service.inativarPessoa(id);

        // Assert
        assertFalse(pessoaAtiva.isAtivo());
        verify(repository).save(pessoaAtiva);
    }

    @Test
    void deveLancarExcecaoAoInativarPessoaInexistente() {
        // Arrange
        Long id = 999L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.inativarPessoa(id);
        });

        assertEquals("Pessoa não encontrada", exception.getMessage());
        verify(repository, never()).save(any());
    }
}