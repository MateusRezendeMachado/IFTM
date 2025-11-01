package com.professorangoti.torresmo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.professorangoti.torresmo.model.Produto;
import com.professorangoti.torresmo.repository.ProdutoRepository;

@SpringBootApplication
public class TorresmoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TorresmoApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(ProdutoRepository produtoRepository) {
		return args -> {
			// Verifica se já existem produtos para não duplicar
			if (produtoRepository.count() == 0) {
				System.out.println("Adicionando produtos iniciais...");

				Produto p1 = new Produto();
				p1.setNome("Torresmo Tradicional");
				p1.setDescricao("Torresmo crocante e saboroso, preparado de forma artesanal");
				p1.setPreco(25.90);
				p1.setTamanho("500g");
				p1.setDisponivel(true);
				produtoRepository.save(p1);

				Produto p2 = new Produto();
				p2.setNome("Torresmo Picante");
				p2.setDescricao("Torresmo temperado com pimenta calabresa e especiarias");
				p2.setPreco(28.90);
				p2.setTamanho("500g");
				p2.setDisponivel(true);
				produtoRepository.save(p2);

				Produto p3 = new Produto();
				p3.setNome("Torresmo com Limão");
				p3.setDescricao("Torresmo finalizado com raspas de limão siciliano");
				p3.setPreco(27.90);
				p3.setTamanho("500g");
				p3.setDisponivel(true);
				produtoRepository.save(p3);

				Produto p4 = new Produto();
				p4.setNome("Torresmo Premium");
				p4.setDescricao("Corte nobre, extra crocante e temperado com ervas finas");
				p4.setPreco(35.90);
				p4.setTamanho("300g");
				p4.setDisponivel(true);
				produtoRepository.save(p4);

				Produto p5 = new Produto();
				p5.setNome("Combo Família");
				p5.setDescricao("1kg de torresmo tradicional + 500g de torresmo picante");
				p5.setPreco(75.00);
				p5.setTamanho("1,5kg");
				p5.setDisponivel(true);
				produtoRepository.save(p5);

				System.out.println("Produtos adicionados com sucesso!");
			} else {
				System.out.println("Produtos já existem no banco de dados.");
			}
		};
	}

}
