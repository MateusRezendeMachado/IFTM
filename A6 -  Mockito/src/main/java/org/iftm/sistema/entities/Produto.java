package org.iftm.sistema.entities;

import java.math.BigDecimal;

public class Produto {
    private Long id;
    private String descricao;
    private BigDecimal preco;
    private Integer quantidadeEstoque;
    private boolean ativo;

    public Produto() {}

    public Produto(Long id, String descricao, BigDecimal preco, Integer quantidadeEstoque, boolean ativo) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.ativo = ativo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }

    public Integer getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(Integer quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}