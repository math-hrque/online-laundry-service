package br.com.lol.lol.exeptions;

public class SituacaoPedidoNaoExisteException extends Exception{
    public SituacaoPedidoNaoExisteException(String mensagem){
        super(mensagem);
    }
}
