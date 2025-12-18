// EmprestimoController.java
package com.bookflow.controller;

import com.bookflow.dto.EmprestimoDTO;
import com.bookflow.model.Emprestimo;
import com.bookflow.model.ItemEmprestimo;
import com.bookflow.model.Livro;
import com.bookflow.model.Aluno;
import com.bookflow.service.EmprestimoService;
import com.bookflow.repository.EmprestimoRepository;
import com.bookflow.repository.AlunoRepository;
import com.bookflow.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {
    
    @Autowired
    private EmprestimoService emprestimoService;
    
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    
    @Autowired
    private AlunoRepository alunoRepository;
    
    @Autowired
    private LivroRepository livroRepository;
    
    @GetMapping
    public String listarEmprestimos(Model model) {
        model.addAttribute("emprestimos", emprestimoRepository.findAll());
        return "emprestimo/list";
    }
    
    @GetMapping("/ativos")
    public String emprestimosAtivos(Model model) {
        model.addAttribute("emprestimos", emprestimoService.buscarEmprestimosAtivos());
        model.addAttribute("titulo", "Empréstimos Ativos");
        return "emprestimo/list";
    }
    
    @GetMapping("/atrasados")
    public String emprestimosAtrasados(Model model) {
        model.addAttribute("emprestimos", emprestimoService.buscarEmprestimosAtrasados());
        model.addAttribute("titulo", "Empréstimos Atrasados");
        return "emprestimo/list";
    }
    
    @GetMapping("/novo")
    public String novoEmprestimo(Model model) {
        EmprestimoDTO dto = new EmprestimoDTO();
        dto.getItens().add(new EmprestimoDTO.ItemEmprestimoDTO());
        
        model.addAttribute("emprestimoDTO", dto);
        model.addAttribute("alunos", alunoRepository.findAll());
        model.addAttribute("livrosDisponiveis", livroRepository.findByQuantidadeExemplaresGreaterThan(0));
        return "emprestimo/form";
    }
    
    @PostMapping("/salvar")
    public String salvarEmprestimo(@ModelAttribute EmprestimoDTO dto, RedirectAttributes redirectAttributes) {
        try {
            Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
            
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setAluno(aluno);
            
            // Converter DTO para entidades
            List<ItemEmprestimo> itens = dto.getItens().stream()
                .filter(item -> item.getLivroId() != null && item.getQuantidade() > 0)
                .map(itemDTO -> {
                    Livro livro = livroRepository.findById(itemDTO.getLivroId())
                        .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
                    
                    ItemEmprestimo item = new ItemEmprestimo();
                    item.setLivro(livro);
                    item.setQuantidade(itemDTO.getQuantidade());
                    item.setEmprestimo(emprestimo);
                    
                    return item;
                })
                .collect(Collectors.toList());
            
            emprestimo.setItens(itens);
            
            emprestimoService.realizarEmprestimo(emprestimo);
            redirectAttributes.addFlashAttribute("success", "Empréstimo realizado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao realizar empréstimo: " + e.getMessage());
        }
        
        return "redirect:/emprestimos";
    }
    
    @PostMapping("/devolver/{id}")
    public String registrarDevolucao(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            emprestimoService.registrarDevolucao(id);
            redirectAttributes.addFlashAttribute("success", "Devolução registrada com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao registrar devolução: " + e.getMessage());
        }
        
        return "redirect:/emprestimos";
    }
    
    @GetMapping("/detalhes/{id}")
    public String detalhesEmprestimo(@PathVariable Long id, Model model) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));
        
        model.addAttribute("emprestimo", emprestimo);
        return "emprestimo/detalhes";
    }
}