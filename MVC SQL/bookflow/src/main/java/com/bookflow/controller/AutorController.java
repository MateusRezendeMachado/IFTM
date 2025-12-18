// AutorController.java
package com.bookflow.controller;

import com.bookflow.model.Autor;
import com.bookflow.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/autores")
public class AutorController {
    
    @Autowired
    private AutorRepository autorRepository;
    
    @GetMapping
    public String listarAutores(Model model) {
        model.addAttribute("autores", autorRepository.findAll());
        return "autor/list";
    }
    
    @GetMapping("/novo")
    public String novoAutor(Model model) {
        model.addAttribute("autor", new Autor());
        return "autor/form";
    }
    
    @PostMapping("/salvar")
    public String salvarAutor(@ModelAttribute Autor autor) {
        autorRepository.save(autor);
        return "redirect:/autores";
    }
    
    @GetMapping("/editar/{id}")
    public String editarAutor(@PathVariable Long id, Model model) {
        model.addAttribute("autor", autorRepository.findById(id).orElseThrow());
        return "autor/form";
    }
    
    @GetMapping("/excluir/{id}")
    public String excluirAutor(@PathVariable Long id) {
        autorRepository.deleteById(id);
        return "redirect:/autores";
    }
}