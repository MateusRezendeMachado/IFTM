package org.iftm.sistema.servicies;

public interface GerenciadorImpressora {

    boolean estaConectado();

    int imprimir(String nomeCompleto, String sigla);

}
