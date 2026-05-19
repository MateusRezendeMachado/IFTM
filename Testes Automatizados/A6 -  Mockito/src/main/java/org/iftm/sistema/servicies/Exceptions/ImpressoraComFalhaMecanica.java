package org.iftm.sistema.servicies.Exceptions;

public class ImpressoraComFalhaMecanica extends IllegalStateException{
    public ImpressoraComFalhaMecanica(){
        super("Impressora apresenta falha mecânica!!!");
    }
}
