package br.edu.iftm.tspi.pbackorm.e_commerce.dto;

import java.time.LocalDate;

public interface ItemPedidoComProdutoDTO {
    Integer getPedidoId();
    LocalDate getDataPedido();
    String getProdutoNome();
    Integer getQuantidade();
    Double getPrecoUnitario();
    Double getSubtotal();
}
