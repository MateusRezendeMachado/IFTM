package org.iftm.sistema.services;

import org.iftm.sistema.entities.Servico;
import org.iftm.sistema.repositories.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;

    // Cadastrar com disponível = true e validações
    public Servico cadastrar(Servico servico) {
        if (servico.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor do serviço deve ser positivo");
        }
        if (servico.getTempoMinutos() <= 0) {
            throw new IllegalArgumentException("Tempo deve ser maior que zero");
        }
        servico.setDisponivel(true);
        return repository.save(servico);
    }

    // Indisponibilizar serviço
    public void indisponibilizarServico(Long id) {
        Servico servico = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
        servico.setDisponivel(false);
        repository.save(servico);
    }

    // Deletar com validação
    public void deletarPorId(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Serviço não encontrado");
        }
        repository.deleteById(id);
    }
}