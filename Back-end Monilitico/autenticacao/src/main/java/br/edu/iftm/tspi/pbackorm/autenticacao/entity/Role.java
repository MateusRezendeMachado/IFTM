package br.edu.iftm.tspi.pbackorm.autenticacao.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "role_permissoes",
        joinColumns = @JoinColumn(name = "role_id"),
        inverseJoinColumns = @JoinColumn(name = "permissao_id")
    )
    private List<Permissao> permissoes;
}
