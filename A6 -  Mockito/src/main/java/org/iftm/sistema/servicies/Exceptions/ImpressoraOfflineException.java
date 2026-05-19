package org.iftm.sistema.servicies.Exceptions;

public class ImpressoraOfflineException extends IllegalStateException{
    public static final int STATUSFALHAMECANICA = 2;

    public ImpressoraOfflineException(){
        super("Impressora OffLine!!!");
    }
}
