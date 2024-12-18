package br.com.lol.lol.exeptions;

public class ListaClienteVaziaException extends Exception{
    public ListaClienteVaziaException(String mensagem){
        super(mensagem);
    }
}
