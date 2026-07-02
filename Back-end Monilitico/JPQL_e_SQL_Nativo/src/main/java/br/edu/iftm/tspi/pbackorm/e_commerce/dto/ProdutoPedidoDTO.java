package br.edu.iftm.tspi.pbackorm.e_commerce.dto;

public interface ProdutoPedidoDTO {
    Integer getPedidoId();
    Integer getQuantidade();
    Double getValorTotal();
    Double getDescontoTotal();
}
