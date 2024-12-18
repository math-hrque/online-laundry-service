package br.com.lol.lol.exeptions;

public class UsuarioJaExisteException extends Exception{
    public UsuarioJaExisteException(String mensagem){
        super(mensagem);
    }
}
