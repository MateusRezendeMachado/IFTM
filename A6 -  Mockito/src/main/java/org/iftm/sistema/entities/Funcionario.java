package org.iftm.sistema.entities;

import java.math.BigDecimal;

public class Funcionario {
    private Long id;
    private String nome;
    private String cargo;
    private BigDecimal salario;
    private boolean emFerias;

    public Funcionario() {}

    public Funcionario(Long id, String nome, String cargo, BigDecimal salario, boolean emFerias) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
        this.emFerias = emFerias;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public BigDecimal getSalario() { return salario; }
    public void setSalario(BigDecimal salario) { this.salario = salario; }

    public boolean isEmFerias() { return emFerias; }
    public void setEmFerias(boolean emFerias) { this.emFerias = emFerias; }
}