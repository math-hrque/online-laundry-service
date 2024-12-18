package br.com.lol.lol.exeptions;

public class ListaPedidoVaziaException extends Exception{
    public ListaPedidoVaziaException(String mensagem){
        super(mensagem);
    }
}
