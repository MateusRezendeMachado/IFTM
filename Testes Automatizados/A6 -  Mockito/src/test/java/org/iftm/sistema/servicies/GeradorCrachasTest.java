package org.iftm.sistema.servicies;

import org.iftm.sistema.entities.Pessoa;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GeradorCrachasTest {

    //Testar o cenário de teste do sucesso da impressão
    @Test
    public void testarImpressaoCrachaComSucessoQuandoImpressoraOnlineESemErros(){
        //arrange
        String nome = "Ana";
        String sobrenome = "Silva";
        String nomeCompleto = "Ana Silva";
        String sigla = "AS";
        Pessoa pessoa = new Pessoa(nome, sobrenome);

        //aqui criamos o mock da classe GerenciadorImpressora
        GerenciadorImpressora gerenciadorImpressoraMock = mock(GerenciadorImpressora.class);

        //configurar o mock
        //definindo o cenário no qual a impressora esta online
        when(gerenciadorImpressoraMock.estaConectado()).thenReturn(true);
        //definindo o cenário da impressão com sucesso, retornando o valor 1.
        when(gerenciadorImpressoraMock.imprimir(nomeCompleto, sigla)).thenReturn(1);
        GeradorCracha geradorCracha = new GeradorCracha(gerenciadorImpressoraMock);

        
        //act e assert
        assertDoesNotThrow(()->{
            geradorCracha.gerarPara(pessoa);
        });

        //verificação - o mockito inspeciona as informações que foram recebidas na classe Mock
        verify(gerenciadorImpressoraMock).imprimir(nomeCompleto, sigla);

    }

    //Testar o cenário de teste no qual a impressora não está online
    @Test
    public void testarImpressaoCrachaNaoImprimeQuandoImpressoraOffline(){
        //arrange
        String nome = "Ana";
        String sobrenome = "Silva";
        String nomeCompleto = "Ana Silva";
        String sigla = "AS";
        Pessoa pessoa = new Pessoa(nome, sobrenome);
        String mensagemErroEsperado = "Impressora OffLine!!!";

        //aqui criamos o mock da classe GerenciadorImpressora
        GerenciadorImpressora gerenciadorImpressoraMock = mock(GerenciadorImpressora.class);

        //configurar o mock
        //definindo o cenário no qual a impressora esta offline
        when(gerenciadorImpressoraMock.estaConectado()).thenReturn(false);

        GeradorCracha geradorCracha = new GeradorCracha(gerenciadorImpressoraMock);

        
        //act e assert
        IllegalStateException e = assertThrows(IllegalStateException.class, 
            ()->{
                geradorCracha.gerarPara(pessoa);
            }
        );

        assertEquals(mensagemErroEsperado, e.getMessage());
        //verificação - se o método imprimir não foi chamado na classe gerenciador de impressora
        verify(gerenciadorImpressoraMock, never()).imprimir(nomeCompleto, sigla);

    }    

    //Testar o cenário de teste no qual a impressora está online e apresenta falha mecanica
    @Test
    public void testarImpressaoCrachaNaoImprimeQuandoImpressoraOnlineEApresentaFalhaMecanica(){
        //arrange
        String nome = "Ana";
        String sobrenome = "Silva";
        String nomeCompleto = "Ana Silva";
        String sigla = "AS";
        Pessoa pessoa = new Pessoa(nome, sobrenome);
        String mensagemErroEsperado = "Impressora apresenta falha mecânica!!!";

        //aqui criamos o mock da classe GerenciadorImpressora
        GerenciadorImpressora gerenciadorImpressoraMock = mock(GerenciadorImpressora.class);

        //configurar o mock
        //definindo o cenário no qual a impressora esta online
        when(gerenciadorImpressoraMock.estaConectado()).thenReturn(true);
        //definindo cenário no qual a impressora relata falha mecânica
        when(gerenciadorImpressoraMock.imprimir(nomeCompleto, sigla)).thenReturn(2);

        GeradorCracha geradorCracha = new GeradorCracha(gerenciadorImpressoraMock);

        
        //act e assert
        IllegalStateException e = assertThrows(IllegalStateException.class, 
            ()->{
                geradorCracha.gerarPara(pessoa);
            }
        );

        assertEquals(mensagemErroEsperado, e.getMessage());
        //verificação - se o método imprimir foi chamado
        verify(gerenciadorImpressoraMock).imprimir(nomeCompleto, sigla);

    }    

}
