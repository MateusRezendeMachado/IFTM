package br.edu.iftm.tspi.pbackorm.e_commerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Data
public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedidoid") private Integer id;
    @Column(name = "datapedido") private LocalDate dataPedido;
    @ManyToOne @JoinColumn(name = "clienteid") private Cliente cliente;
    @OneToMany(mappedBy = "pedido")
    private List<DetalhesPedido> detalhes;
}
