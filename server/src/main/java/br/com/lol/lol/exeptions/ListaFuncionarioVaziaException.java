package br.com.lol.lol.exeptions;

public class ListaFuncionarioVaziaException extends Exception{
    public ListaFuncionarioVaziaException(String mensagem){
        super(mensagem);
    }
}
