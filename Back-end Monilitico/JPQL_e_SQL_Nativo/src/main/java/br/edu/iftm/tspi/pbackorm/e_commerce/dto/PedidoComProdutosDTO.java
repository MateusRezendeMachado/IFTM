package br.edu.iftm.tspi.pbackorm.e_commerce.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class PedidoComProdutosDTO {
    private Integer pedidoId;
    private LocalDate dataPedido;
    private List<ItemPedidoDTO> itens;
    private Double valorTotal = 0.0;

    @Data
    public static class ItemPedidoDTO {
        private String produtoNome;
        private Integer quantidade;
        private Double precoUnitario;
        private Double subtotal;
    }
}
