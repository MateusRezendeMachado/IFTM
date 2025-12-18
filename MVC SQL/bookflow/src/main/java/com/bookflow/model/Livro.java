// Livro.java
package com.bookflow.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String titulo;
    private Integer anoPublicacao;
    private String isbn;
    private Integer quantidadeExemplares;
    
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
}