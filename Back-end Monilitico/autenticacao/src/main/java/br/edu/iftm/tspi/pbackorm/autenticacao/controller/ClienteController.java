package br.edu.iftm.tspi.pbackorm.autenticacao.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    // Simulação de dados
    private static final List<Map<String, Object>> clientes = List.of(
            Map.of("id", 1, "nome", "João"),
            Map.of("id", 2, "nome", "Maria")
    );

    @GetMapping
    public List<Map<String, Object>> listar() {
        return clientes;
    }

    @PostMapping
    public Map<String, String> criar(@RequestBody Map<String, String> novo) {
        return Map.of("mensagem", "Cliente criado: " + novo.get("nome"));
    }
}
