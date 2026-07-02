package br.edu.iftm.tspi.pbackorm.e_commerce.controller;

import br.edu.iftm.tspi.pbackorm.e_commerce.dto.*;
import br.edu.iftm.tspi.pbackorm.e_commerce.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/cliente/{id}/total-por-produto")
    public ResponseEntity<List<ClienteProdutoTotalDTO>> getTotalPorProduto(@PathVariable("id") Integer clienteId) {
        List<ClienteProdutoTotalDTO> lista = pedidoService.getTotalGastoPorCliente(clienteId);
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @GetMapping("/cliente/{id}/pedidos-periodo")
    public ResponseEntity<List<PedidoComProdutosDTO>> getPedidosClientePeriodo(
            @PathVariable("id") Integer clienteId,
            @RequestParam("inicio") String inicio,
            @RequestParam("fim") String fim) {
        List<PedidoComProdutosDTO> lista = pedidoService.getPedidosComItens(
                clienteId, LocalDate.parse(inicio), LocalDate.parse(fim));
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }
}
