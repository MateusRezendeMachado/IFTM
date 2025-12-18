// DataLoader.java
package com.bookflow.config;

import com.bookflow.model.Autor;
import com.bookflow.model.Livro;
import com.bookflow.model.Aluno;
import com.bookflow.repository.AutorRepository;
import com.bookflow.repository.LivroRepository;
import com.bookflow.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    
    @Autowired
    private AutorRepository autorRepository;
    
    @Autowired
    private LivroRepository livroRepository;
    
    @Autowired
    private AlunoRepository alunoRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Criar autores
        Autor autor1 = new Autor();
        autor1.setNome("Machado de Assis");
        autor1.setNacionalidade("Brasileiro");
        autorRepository.save(autor1);
        
        Autor autor2 = new Autor();
        autor2.setNome("Clarice Lispector");
        autor2.setNacionalidade("Brasileira");
        autorRepository.save(autor2);
        
        // Criar livros
        Livro livro1 = new Livro();
        livro1.setTitulo("Dom Casmurro");
        livro1.setAnoPublicacao(1899);
        livro1.setIsbn("978-85-06-00000-1");
        livro1.setQuantidadeExemplares(5);
        livro1.setAutor(autor1);
        livroRepository.save(livro1);
        
        Livro livro2 = new Livro();
        livro2.setTitulo("A Hora da Estrela");
        livro2.setAnoPublicacao(1977);
        livro2.setIsbn("978-85-06-00000-2");
        livro2.setQuantidadeExemplares(3);
        livro2.setAutor(autor2);
        livroRepository.save(livro2);
        
        // Criar alunos
        Aluno aluno1 = new Aluno();
        aluno1.setNome("João Silva");
        aluno1.setMatricula("20230001");
        aluno1.setEmail("joao@email.com");
        aluno1.setTelefone("(34) 99999-9999");
        alunoRepository.save(aluno1);
        
        Aluno aluno2 = new Aluno();
        aluno2.setNome("Maria Santos");
        aluno2.setMatricula("20230002");
        aluno2.setEmail("maria@email.com");
        aluno2.setTelefone("(34) 98888-8888");
        alunoRepository.save(aluno2);
    }
}