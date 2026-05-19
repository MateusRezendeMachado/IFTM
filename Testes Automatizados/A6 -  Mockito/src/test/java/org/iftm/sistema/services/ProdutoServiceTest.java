package org.iftm.sistema.services;

import org.iftm.sistema.entities.Produto;
import org.iftm.sistema.repositories.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository repository;

    @InjectMocks
    private ProdutoService service;

    private Produto produtoValido;

    @BeforeEach
    void setUp() {
        produtoValido = new Produto(null, "Teclado Mecânico", BigDecimal.valueOf(150.00), 10, false);
    }

    // ========== DESAFIO 1: (Adaptado para Produto) ==========
    // Produto não tem busca por nome no requisito, mas podemos testar listagem geral
    @Test
    void deveBuscarTodosOsProdutos() {
        // Arrange
        when(repository.findAll()).thenReturn(java.util.Arrays.asList(
            new Produto(1L, "Mouse", BigDecimal.valueOf(50), 5, true),
            new Produto(2L, "Teclado", BigDecimal.valueOf(150), 3, true)
        ));

        // Act
        var resultado = repository.findAll();

        // Assert
        assertEquals(2, resultado.size());
        verify(repository).findAll();
    }

    // ========== DESAFIO 2: PROTEGER EXCLUSÃO ==========
    @Test
    void deveLancarExcecaoAoApagarProdutoQuandoIdNaoExistir() {
        Long idInexistente = 999L;
        when(repository.existsById(idInexistente)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.deletarPorId(idInexistente);
        });

        assertEquals("Produto não encontrado", exception.getMessage());
        verify(repository, never()).deleteById(any());
    }

    // ========== CICLO 1: STATUS PADRÃO (ATIVO = TRUE) ==========
    @Test
    void deveCadastrarProdutoComStatusAtivoTrue() {
        Produto produtoSalvo = new Produto(1L, "Teclado Mecânico", BigDecimal.valueOf(150.00), 10, true);
        when(repository.save(any(Produto.class))).thenReturn(produtoSalvo);

        Produto resultado = service.cadastrar(produtoValido);

        assertTrue(resultado.isAtivo());
        verify(repository).save(any(Produto.class));
    }

    // ========== CICLO 2: VALIDAÇÕES ==========
    @Test
    void deveLancarExcecaoSePrecoNegativo() {
        Produto produtoInvalido = new Produto(null, "Produto Ruim", BigDecimal.valueOf(-10), 5, false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.cadastrar(produtoInvalido);
        });

        assertEquals("Preço não pode ser negativo", exception.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoSeEstoqueNegativo() {
        Produto produtoInvalido = new Produto(null, "Produto Ruim", BigDecimal.valueOf(100), -5, false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.cadastrar(produtoInvalido);
        });

        assertEquals("Estoque não pode ser negativo", exception.getMessage());
        verify(repository, never()).save(any());
    }

    // ========== CICLO 3: AÇÃO ESPECÍFICA (INATIVAR) ==========
    @Test
    void deveInativarProdutoComSucesso() {
        Long id = 1L;
        Produto produtoAtivo = new Produto(id, "Teclado", BigDecimal.valueOf(150), 10, true);
        when(repository.findById(id)).thenReturn(Optional.of(produtoAtivo));
        when(repository.save(any(Produto.class))).thenReturn(produtoAtivo);

        service.inativarProduto(id);

        assertFalse(produtoAtivo.isAtivo());
        verify(repository).save(produtoAtivo);
    }

    @Test
    void deveLancarExcecaoAoInativarProdutoInexistente() {
        Long id = 999L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.inativarProduto(id);
        });

        assertEquals("Produto não encontrado", exception.getMessage());
        verify(repository, never()).save(any());
    }
}