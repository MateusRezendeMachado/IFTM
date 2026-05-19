package org.iftm.sistema.entities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaApplication.class, args);
        System.out.println(" Sistema rodando em: http://localhost:8080");
        System.out.println(" Console H2: http://localhost:8080/h2-console");
        System.out.println("   JDBC URL: jdbc:h2:mem:testdb | User: sa | Password: (vazio)");
    }
}