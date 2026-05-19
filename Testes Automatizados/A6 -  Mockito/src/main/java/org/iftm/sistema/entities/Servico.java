package org.iftm.sistema.entities;

import java.math.BigDecimal;

public class Servico {
    private Long id;
    private String nome;
    private BigDecimal valor;
    private Integer tempoMinutos;
    private boolean disponivel;

    public Servico() {}

    public Servico(Long id, String nome, BigDecimal valor, Integer tempoMinutos, boolean disponivel) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.tempoMinutos = tempoMinutos;
        this.disponivel = disponivel;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public Integer getTempoMinutos() { return tempoMinutos; }
    public void setTempoMinutos(Integer tempoMinutos) { this.tempoMinutos = tempoMinutos; }

    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
}