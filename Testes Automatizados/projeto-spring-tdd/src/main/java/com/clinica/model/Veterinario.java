package com.clinica.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "veterinario")
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Double salario;

    private Instant dataNascimento;

    @Column(unique = true)
    private String email;

    // construtores, getters e setters
    public Veterinario() {}

    public Veterinario(String nome, Double salario, Instant dataNascimento, String email) {
        this.nome = nome;
        this.salario = salario;
        this.dataNascimento = dataNascimento;
        this.email = email;
    }

    // getters e setters omitidos para brevidade (gerar no IDE)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Double getSalario() { return salario; }
    public void setSalario(Double salario) { this.salario = salario; }
    public Instant getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(Instant dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}