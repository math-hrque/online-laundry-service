package br.com.lol.lol.exeptions;

public class FuncionarioNaoExisteException extends Exception{
    public FuncionarioNaoExisteException(String mensagem){
        super(mensagem);
    }
}
