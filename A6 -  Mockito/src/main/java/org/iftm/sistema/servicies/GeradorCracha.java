
package org.iftm.sistema.servicies;

import org.iftm.sistema.entities.Pessoa;
import org.iftm.sistema.servicies.Exceptions.ImpressoraComFalhaMecanica;
import org.iftm.sistema.servicies.Exceptions.ImpressoraOfflineException;

class GeradorCracha {
    private GerenciadorImpressora gerenciadorImpressora;

    public GeradorCracha(GerenciadorImpressora gerenciadorImpressora) {
        this.gerenciadorImpressora = gerenciadorImpressora;
    }

    public void gerarPara(Pessoa pessoa) {
        if (!gerenciadorImpressora.estaConectado()){
            throw new ImpressoraOfflineException();
        }
        int retorno = gerenciadorImpressora.imprimir(pessoa.retornarNomeCompleto(), pessoa.retornarIniciais());
        if (retorno == ImpressoraOfflineException.STATUSFALHAMECANICA){
            throw new ImpressoraComFalhaMecanica();
        }
    }

}
    