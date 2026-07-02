package br.edu.iftm.tspi.pbackorm.e_commerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "detalhes_pedido")
@Data
public class DetalhesPedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detalheid") private Integer id;
    @ManyToOne @JoinColumn(name = "pedidoid") private Pedido pedido;
    @ManyToOne @JoinColumn(name = "produtoid") private Produto produto;
    private Integer quantidade;
    @Column(name = "precounitario") private Double precoUnitario;
    private Double desconto;
}
