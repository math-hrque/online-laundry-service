package br.com.lol.lol.exeptions;

public class CepNaoExisteException extends Exception{
    public CepNaoExisteException(String mensagem){
        super(mensagem);
    }
}
