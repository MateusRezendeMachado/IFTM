package org.iftm.sistema.services;

import org.iftm.sistema.entities.Pessoa;
import org.iftm.sistema.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    // Salvar com validação de email e status padrão
    public Pessoa salvar(Pessoa pessoa) {
        if (repository.existsByEmail(pessoa.getEmail())) {
            throw new IllegalArgumentException("E-mail já cadastrado!");
        }
        pessoa.setAtivo(true);
        return repository.save(pessoa);
    }

    // Buscar por parte do sobrenome
    public List<Pessoa> buscarPorSobrenome(String sobrenome) {
        return repository.findBySobrenomeContaining(sobrenome);
    }

    // Deletar com validação de existência
    public void deletarPorId(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Pessoa não encontrada");
        }
        repository.deleteById(id);
    }

    // Aplicar status específico
    public void inativarPessoa(Long id) {
        Pessoa pessoa = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        pessoa.setAtivo(false);
        repository.save(pessoa);
    }
}