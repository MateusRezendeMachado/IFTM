package br.edu.iftm.tspi.pbackorm.e_commerce.service;

import br.edu.iftm.tspi.pbackorm.e_commerce.dto.*;
import br.edu.iftm.tspi.pbackorm.e_commerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<ClienteProdutoTotalDTO> getTotalGastoPorCliente(Integer clienteId) {
        return pedidoRepository.findTotalGastoPorCliente(clienteId);
    }

    public List<PedidoComProdutosDTO> getPedidosComItens(Integer clienteId, LocalDate inicio, LocalDate fim) {
        List<ItemPedidoComProdutoDTO> itens = pedidoRepository.findItensPedidoPorClienteEPeriodo(clienteId, inicio, fim);
        Map<Integer, PedidoComProdutosDTO> mapa = new LinkedHashMap<>();
        for (ItemPedidoComProdutoDTO item : itens) {
            PedidoComProdutosDTO pedidoDTO = mapa.computeIfAbsent(item.getPedidoId(), id -> {
                PedidoComProdutosDTO dto = new PedidoComProdutosDTO();
                dto.setPedidoId(id);
                dto.setDataPedido(item.getDataPedido());
                dto.setItens(new ArrayList<>());
                return dto;
            });
            PedidoComProdutosDTO.ItemPedidoDTO itemDTO = new PedidoComProdutosDTO.ItemPedidoDTO();
            itemDTO.setProdutoNome(item.getProdutoNome());
            itemDTO.setQuantidade(item.getQuantidade());
            itemDTO.setPrecoUnitario(item.getPrecoUnitario());
            itemDTO.setSubtotal(item.getSubtotal());
            pedidoDTO.getItens().add(itemDTO);
            pedidoDTO.setValorTotal(pedidoDTO.getValorTotal() + item.getSubtotal());
        }
        return new ArrayList<>(mapa.values());
    }
}
