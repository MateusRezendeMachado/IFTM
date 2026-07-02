package br.edu.iftm.tspi.pbackorm.e_commerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "categorias")
@Data
public class Categoria {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoriaid") private Integer id;
    private String nome;
    private String descricao;
    @OneToMany(mappedBy = "categoria")
    private List<Produto> produtos;
}
