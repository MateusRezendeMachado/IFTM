package br.edu.iftm.tspi.pbackorm.autenticacao.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "permissoes")
@Data
public class Permissao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
}
