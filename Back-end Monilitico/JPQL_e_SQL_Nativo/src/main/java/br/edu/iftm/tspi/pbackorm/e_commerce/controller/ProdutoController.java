package br.edu.iftm.tspi.pbackorm.e_commerce.controller;

import br.edu.iftm.tspi.pbackorm.e_commerce.dto.*;
import br.edu.iftm.tspi.pbackorm.e_commerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/unidades-compradas")
    public ResponseEntity<List<UnidadesCompradasDTO>> getUnidadesCompradas(
            @RequestParam("inicio") String inicio,
            @RequestParam("fim") String fim,
            @RequestParam(value = "produtoId", required = false) Integer produtoId) {
        List<UnidadesCompradasDTO> lista = produtoService.getUnidadesCompradas(
                LocalDate.parse(inicio), LocalDate.parse(fim), produtoId);
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}/detalhes-pedidos")
    public ResponseEntity<List<ProdutoPedidoDTO>> getDetalhesPedidos(@PathVariable("id") Integer produtoId) {
        List<ProdutoPedidoDTO> lista = produtoService.getDetalhesPedidosPorProduto(produtoId);
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @GetMapping("/categoria/{id}/total-vendido")
    public ResponseEntity<CategoriaTotalDTO> getTotalVendidoCategoria(@PathVariable("id") Integer categoriaId) {
        CategoriaTotalDTO dto = produtoService.getTotalVendidoPorCategoria(categoriaId);
        return dto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }
}
