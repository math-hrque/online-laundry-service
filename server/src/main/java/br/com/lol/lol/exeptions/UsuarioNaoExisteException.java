package br.com.lol.lol.exeptions;

public class UsuarioNaoExisteException extends Exception{
    public UsuarioNaoExisteException(String mensagem){
        super(mensagem);
    }
}
