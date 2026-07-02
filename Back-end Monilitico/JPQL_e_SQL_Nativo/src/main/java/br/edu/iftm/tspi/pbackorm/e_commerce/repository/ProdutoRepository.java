package br.edu.iftm.tspi.pbackorm.e_commerce.repository;

import br.edu.iftm.tspi.pbackorm.e_commerce.entity.Produto;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query("""
           SELECT p.nome as nomeProduto, SUM(dp.quantidade) as unidadesCompradas
           FROM DetalhesPedido dp
           JOIN dp.produto p
           JOIN dp.pedido ped
           WHERE ped.dataPedido BETWEEN :dataInicio AND :dataFim
             AND (:produtoId IS NULL OR p.id = :produtoId)
           GROUP BY p.nome
           ORDER BY unidadesCompradas DESC
           """)
    List<UnidadesCompradasDTO> findUnidadesCompradasPeriodo(
            @Param("dataInicio") LocalDate inicio,
            @Param("dataFim") LocalDate fim,
            @Param("produtoId") Integer produtoId);

    @Query("""
           SELECT dp.pedido.id as pedidoId,
                  dp.quantidade as quantidade,
                  (dp.quantidade * dp.precoUnitario) as valorTotal,
                  dp.desconto as descontoTotal
           FROM DetalhesPedido dp
           WHERE dp.produto.id = :produtoId
           """)
    List<ProdutoPedidoDTO> findDetalhesPorProduto(@Param("produtoId") Integer produtoId);

    @Query("""
           SELECT c.nome as categoriaNome,
                  SUM(dp.quantidade * dp.precoUnitario) as totalVendido
           FROM DetalhesPedido dp
           JOIN dp.produto p
           JOIN p.categoria c
           WHERE c.id = :categoriaId
           """)
    CategoriaTotalDTO findTotalVendidoPorCategoria(@Param("categoriaId") Integer categoriaId);
}
