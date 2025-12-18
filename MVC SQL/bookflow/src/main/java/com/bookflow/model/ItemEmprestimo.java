// ItemEmprestimo.java
package com.bookflow.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ItemEmprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "emprestimo_id")
    private Emprestimo emprestimo;
    
    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;
    
    private Integer quantidade;
}