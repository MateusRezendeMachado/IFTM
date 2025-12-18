// LivroController.java
package com.bookflow.controller;

import com.bookflow.model.Livro;
import com.bookflow.model.Autor;
import com.bookflow.repository.LivroRepository;
import com.bookflow.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/livros")
public class LivroController {
    
    @Autowired
    private LivroRepository livroRepository;
    
    @Autowired
    private AutorRepository autorRepository;
    
    @GetMapping
    public String listarLivros(Model model) {
        model.addAttribute("livros", livroRepository.findAll());
        return "livro/list";
    }
    
    @GetMapping("/novo")
    public String novoLivro(Model model) {
        model.addAttribute("livro", new Livro());
        model.addAttribute("autores", autorRepository.findAll());
        return "livro/form";
    }
    
    @PostMapping("/salvar")
    public String salvarLivro(@ModelAttribute Livro livro) {
        livroRepository.save(livro);
        return "redirect:/livros";
    }
    
    @GetMapping("/editar/{id}")
    public String editarLivro(@PathVariable Long id, Model model) {
        Livro livro = livroRepository.findById(id).orElseThrow();
        model.addAttribute("livro", livro);
        model.addAttribute("autores", autorRepository.findAll());
        return "livro/form";
    }
    
    @GetMapping("/excluir/{id}")
    public String excluirLivro(@PathVariable Long id) {
        livroRepository.deleteById(id);
        return "redirect:/livros";
    }
    
    @GetMapping("/disponiveis")
    public String livrosDisponiveis(Model model) {
        List<Livro> livrosDisponiveis = livroRepository.findByQuantidadeExemplaresGreaterThan(0);
        model.addAttribute("livros", livrosDisponiveis);
        model.addAttribute("titulo", "Livros Disponíveis para Empréstimo");
        return "livro/list";
    }
}