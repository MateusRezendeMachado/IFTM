// HomeController.java
package com.bookflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("titulo", "BookFlow - Sistema de Biblioteca");
        model.addAttribute("mensagem", "Bem-vindo ao sistema de gestão de biblioteca");
        return "home";
    }
}