package br.edu.iftm.tspi.pbackorm.e_commerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "produtos")
@Data
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produtoid") private Integer id;
    @Column(name = "produtonome") private String nome;
    private Double preco;
    @Column(name = "unidadesemestoque") private Integer estoque;
    @Column(name = "imagem") private String caminhoImagem;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoriaid", nullable = false)
    private Categoria categoria;
}
