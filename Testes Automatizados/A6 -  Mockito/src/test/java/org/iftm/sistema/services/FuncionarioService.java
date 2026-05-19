package org.iftm.sistema.services;

import org.iftm.sistema.entities.Funcionario;
import org.iftm.sistema.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    private static final BigDecimal SALARIO_MINIMO = BigDecimal.valueOf(1412.00);

    public Funcionario cadastrar(Funcionario funcionario) {
        if (funcionario.getSalario().compareTo(SALARIO_MINIMO) < 0) {
            throw new IllegalArgumentException("Salário abaixo do mínimo permitido");
        }
        funcionario.setEmFerias(false);
        return repository.save(funcionario);
    }

    public void concederFerias(Long id) {
        Funcionario funcionario = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        
        if (funcionario.isEmFerias()) {
            throw new RuntimeException("Funcionário já está de férias");
        }
        
        funcionario.setEmFerias(true);
        repository.save(funcionario);
    }

    public void deletarPorId(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Funcionário não encontrado");
        }
        repository.deleteById(id);
    }
