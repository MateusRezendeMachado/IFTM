package br.edu.iftm.tspi.pbackorm.e_commerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "clientes")
@Data
public class Cliente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clienteid") private Integer id;
    private String nome;
    private String email;
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;
}
