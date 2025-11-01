/*
PRODUTO {
    BIGSERIAL id_produto PK
    VARCHAR   nome
    TEXT      descricao
    NUMERIC   preco
    VARCHAR   tamanho
    BOOLEAN   disponivel
  }
*/

package com.professorangoti.torresmo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduto;
    private String nome;
    private String descricao;
    private Double preco;
    private String tamanho;
    private Boolean disponivel;
    private Boolean destaque = false; 
}
