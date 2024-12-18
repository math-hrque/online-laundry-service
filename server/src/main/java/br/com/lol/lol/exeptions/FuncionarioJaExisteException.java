package br.com.lol.lol.exeptions;

public class FuncionarioJaExisteException extends Exception{
    public FuncionarioJaExisteException(String mensagem){
        super(mensagem);
    }
}
