package org.iftm.sistema.entities;

public class Pessoa {
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private boolean ativo;

    // Construtor padrão (necessário para o JPA)
    public Pessoa() {}

    // Construtor para os testes existentes (nome + sobrenome)
    public Pessoa(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.ativo = true;
    }

    // Construtor completo
    public Pessoa(Long id, String nome, String sobrenome, String email, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.ativo = ativo;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSobrenome() { return sobrenome; }
    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    // Métodos auxiliares (como no seu código original)
    public String retornarNomeCompleto() {
        return this.nome + " " + this.sobrenome;
    }

    public String retornarIniciais() {
        return (this.nome.charAt(0) + "" + this.sobrenome.charAt(0)).toUpperCase();
    }
}