package br.com.lol.lol.exeptions;

public class RoupaNaoExisteException extends Exception{
    public RoupaNaoExisteException(String mensagem){
        super(mensagem);
    }
}
