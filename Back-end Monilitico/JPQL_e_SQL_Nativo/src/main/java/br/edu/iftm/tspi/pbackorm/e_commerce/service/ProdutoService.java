package br.edu.iftm.tspi.pbackorm.e_commerce.service;

import br.edu.iftm.tspi.pbackorm.e_commerce.dto.*;
import br.edu.iftm.tspi.pbackorm.e_commerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<UnidadesCompradasDTO> getUnidadesCompradas(LocalDate inicio, LocalDate fim, Integer produtoId) {
        return produtoRepository.findUnidadesCompradasPeriodo(inicio, fim, produtoId);
    }

    public List<ProdutoPedidoDTO> getDetalhesPedidosPorProduto(Integer produtoId) {
        return produtoRepository.findDetalhesPorProduto(produtoId);
    }

    public CategoriaTotalDTO getTotalVendidoPorCategoria(Integer categoriaId) {
        return produtoRepository.findTotalVendidoPorCategoria(categoriaId);
    }
}
