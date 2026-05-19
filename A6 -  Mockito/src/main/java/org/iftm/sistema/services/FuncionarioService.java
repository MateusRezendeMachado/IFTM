package org.iftm.sistema.services;

import java.math.BigDecimal;

import org.iftm.sistema.entities.Funcionario;
import org.iftm.sistema.repositories.FuncionarioRepository;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repository;

    private static final BigDecimal SALARIO_MINIMO = BigDecimal.valueOf(1412.00);

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public Funcionario cadastrar(Funcionario funcionario) {
        if (funcionario.getSalario().compareTo(SALARIO_MINIMO) < 0) {
            throw new IllegalArgumentException("Salário abaixo do mínimo permitido");
        }

        funcionario.setEmFerias(false);
        return repository.save(funcionario);
    }

    public void concederFerias(Integer id) {
        Funcionario funcionario = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado"));

        if (funcionario.isEmFerias()) {
            throw new IllegalStateException("Funcionário já está de férias");
        }

        funcionario.setEmFerias(true);
        repository.save(funcionario);
    }

    public void deletarPorId(Integer id) {
        Funcionario funcionario = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado"));

        repository.delete(funcionario);
    }
}