// DashboardController.java
package com.bookflow.controller;

import com.bookflow.repository.LivroRepository;
import com.bookflow.repository.EmprestimoRepository;
import com.bookflow.repository.AlunoRepository;
import com.bookflow.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class DashboardController {
    
    @Autowired
    private LivroRepository livroRepository;
    
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    
    @Autowired
    private AlunoRepository alunoRepository;
    
    @Autowired
    private EmprestimoService emprestimoService;
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Estatísticas básicas
        long totalLivros = livroRepository.count();
        long totalAlunos = alunoRepository.count();
        long totalEmprestimos = emprestimoRepository.count();
        
        
        // Empréstimos ativos e atrasados
        long emprestimosAtivos = emprestimoService.buscarEmprestimosAtivos().size();
        long emprestimosAtrasados = emprestimoService.buscarEmprestimosAtrasados().size();
        
        model.addAttribute("totalLivros", totalLivros);
        model.addAttribute("totalAlunos", totalAlunos);
        model.addAttribute("totalEmprestimos", totalEmprestimos);
        model.addAttribute("emprestimosAtivos", emprestimosAtivos);
        model.addAttribute("emprestimosAtrasados", emprestimosAtrasados);
        model.addAttribute("dataAtual", LocalDate.now());
        
        return "dashboard";
    }
    
    @GetMapping("/relatorios")
    public String relatorios(Model model) {
        model.addAttribute("titulo", "Relatórios - BookFlow");
        return "relatorios";
    }
}