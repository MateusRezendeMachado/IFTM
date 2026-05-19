package org.iftm.sistema.services;

import org.iftm.sistema.entities.Produto;
import org.iftm.sistema.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    // Cadastrar com status ativo = true e validação de preço
    public Produto cadastrar(Produto produto) {
        if (produto.getPreco().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo");
        }
        if (produto.getQuantidadeEstoque() < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo");
        }
        produto.setAtivo(true);
        return repository.save(produto);
    }

    // Inativar produto
    public void inativarProduto(Long id) {
        Produto produto = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produto.setAtivo(false);
        repository.save(produto);
    }

    // Deletar com validação
    public void deletarPorId(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado");
        }
        repository.deleteById(id);
    }
}