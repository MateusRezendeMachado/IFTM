package br.edu.iftm.tspi.pbackorm.e_commerce.repository;

import br.edu.iftm.tspi.pbackorm.e_commerce.entity.Pedido;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Query("""
           SELECT p.nome as produtoNome,
                  SUM(dp.quantidade * dp.precoUnitario) as totalGasto
           FROM DetalhesPedido dp
           JOIN dp.pedido ped
           JOIN dp.produto p
           WHERE ped.cliente.id = :clienteId
           GROUP BY p.nome
           ORDER BY totalGasto DESC
           """)
    List<ClienteProdutoTotalDTO> findTotalGastoPorCliente(@Param("clienteId") Integer clienteId);

    @Query("""
           SELECT dp.pedido.id as pedidoId,
                  dp.pedido.dataPedido as dataPedido,
                  p.nome as produtoNome,
                  dp.quantidade as quantidade,
                  dp.precoUnitario as precoUnitario,
                  (dp.quantidade * dp.precoUnitario) as subtotal
           FROM DetalhesPedido dp
           JOIN dp.produto p
           WHERE dp.pedido.cliente.id = :clienteId
             AND dp.pedido.dataPedido BETWEEN :dataInicio AND :dataFim
           ORDER BY dp.pedido.id
           """)
    List<ItemPedidoComProdutoDTO> findItensPedidoPorClienteEPeriodo(
            @Param("clienteId") Integer clienteId,
            @Param("dataInicio") LocalDate inicio,
            @Param("dataFim") LocalDate fim);
}
