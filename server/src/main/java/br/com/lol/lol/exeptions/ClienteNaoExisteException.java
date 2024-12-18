package br.com.lol.lol.exeptions;

public class ClienteNaoExisteException extends Exception{
    public ClienteNaoExisteException(String mensagem){
        super(mensagem);
    }
}
