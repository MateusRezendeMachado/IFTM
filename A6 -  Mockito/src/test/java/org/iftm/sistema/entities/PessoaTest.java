package org.iftm.sistema.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class PessoaTest {

    @Test
    public void testarRetornarNomeCompleto() {
        Pessoa p = new Pessoa("Bruno", "Queiroz");
        assertEquals("Bruno Queiroz", p.retornarNomeCompleto());
    }

    @Test
    public void testarRetornarIniciais() {
        Pessoa p = new Pessoa("Bruno", "Queiroz");
        assertEquals("BQ", p.retornarIniciais());
    }

    @Test
    public void testarRetornarIniciaisMinusculo() {
        Pessoa p = new Pessoa("bruno", "queiroz");
        assertEquals("BQ", p.retornarIniciais());
    }
}