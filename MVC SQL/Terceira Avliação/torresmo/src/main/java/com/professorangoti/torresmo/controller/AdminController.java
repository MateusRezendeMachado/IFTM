package com.professorangoti.torresmo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.professorangoti.torresmo.model.Produto;
import com.professorangoti.torresmo.service.ProdutoService;

@Controller
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  private ProdutoService produtoService;

  @GetMapping
  public String admin(Model model) {
    System.out.println("Entrou no metodo admin do AdminController");
    List<Produto> produtos = produtoService.findAll();
    model.addAttribute("produtos", produtos);

    // Adiciona um objeto vazio para o formulário (command object)
    if (!model.containsAttribute("produto")) {
      model.addAttribute("produto", new Produto());
    }

    return "admin";
  }

  @PostMapping("/produtos")
  public String salvarProduto(Produto produto) {
    // Se tem ID, é update; senão é create
    if (produto.getIdProduto() != null) {
      Produto produtoExistente = produtoService.findById(produto.getIdProduto())
          .orElse(new Produto());
      produtoExistente.setNome(produto.getNome());
      produtoExistente.setDescricao(produto.getDescricao());
      produtoExistente.setPreco(produto.getPreco());
      produtoExistente.setTamanho(produto.getTamanho());
      produtoExistente.setDisponivel(produto.getDisponivel() != null ? produto.getDisponivel() : true);
      produtoService.save(produtoExistente);
    } else {
      produto.setDisponivel(produto.getDisponivel() != null ? produto.getDisponivel() : true);
      produtoService.save(produto);
    }

    return "redirect:/admin";
  }

  @PostMapping("/produtos/{id}/delete")
  public String deletarProduto(@PathVariable("id") Long id) {
    produtoService.deleteById(id);
    return "redirect:/admin";
  }

}
