// EmprestimoService.java
package com.bookflow.service;

import com.bookflow.model.Emprestimo;
import com.bookflow.model.ItemEmprestimo;
import com.bookflow.model.Livro;
import com.bookflow.repository.EmprestimoRepository;
import com.bookflow.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {
    
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    
    @Autowired
    private LivroRepository livroRepository;
    
    @Transactional
    public Emprestimo realizarEmprestimo(Emprestimo emprestimo) {
        // Definir datas
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataPrevistaDevolucao(LocalDate.now().plusDays(14));
        
        // Atualizar quantidade de exemplares
        for (ItemEmprestimo item : emprestimo.getItens()) {
            Livro livro = item.getLivro();
            livro.setQuantidadeExemplares(livro.getQuantidadeExemplares() - item.getQuantidade());
            livroRepository.save(livro);
            
            // Vincular item ao empréstimo
            item.setEmprestimo(emprestimo);
        }
        
        return emprestimoRepository.save(emprestimo);
    }
    
    @Transactional
    public void registrarDevolucao(Long emprestimoId) {
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId)
            .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));
        
        if (emprestimo.getDataDevolucaoReal() == null) {
            emprestimo.setDataDevolucaoReal(LocalDate.now());
            
            // Restaurar quantidade de exemplares
            for (ItemEmprestimo item : emprestimo.getItens()) {
                Livro livro = item.getLivro();
                livro.setQuantidadeExemplares(livro.getQuantidadeExemplares() + item.getQuantidade());
                livroRepository.save(livro);
            }
            
            emprestimoRepository.save(emprestimo);
        }
    }
    
    public List<Emprestimo> buscarEmprestimosAtivos() {
        return emprestimoRepository.findByDataDevolucaoRealIsNull();
    }
    
    public List<Emprestimo> buscarEmprestimosAtrasados() {
        return emprestimoRepository.findEmprestimosAtrasados();
    }
}