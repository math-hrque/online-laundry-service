package br.com.lol.lol.exeptions;

public class PedidoNaoExisteException extends Exception{
    public PedidoNaoExisteException(String mensagem){
        super(mensagem);
    }
}
