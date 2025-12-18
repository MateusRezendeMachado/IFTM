// AlunoController.java
package com.bookflow.controller;

import com.bookflow.model.Aluno;
import com.bookflow.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alunos")
public class AlunoController {
    
    @Autowired
    private AlunoRepository alunoRepository;
    
    @GetMapping
    public String listarAlunos(Model model) {
        model.addAttribute("alunos", alunoRepository.findAll());
        return "aluno/list";
    }
    
    @GetMapping("/novo")
    public String novoAluno(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "aluno/form";
    }
    
    @PostMapping("/salvar")
    public String salvarAluno(@ModelAttribute Aluno aluno) {
        alunoRepository.save(aluno);
        return "redirect:/alunos";
    }
    
    @GetMapping("/editar/{id}")
    public String editarAluno(@PathVariable Long id, Model model) {
        model.addAttribute("aluno", alunoRepository.findById(id).orElseThrow());
        return "aluno/form";
    }
    
    @GetMapping("/excluir/{id}")
    public String excluirAluno(@PathVariable Long id) {
        alunoRepository.deleteById(id);
        return "redirect:/alunos";
    }
}