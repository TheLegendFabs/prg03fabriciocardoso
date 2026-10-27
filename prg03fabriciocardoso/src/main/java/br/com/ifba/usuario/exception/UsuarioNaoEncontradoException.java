package br.com.ifba.usuario.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(String identificacao) {
        super("Usuário não encontrado: " + identificacao + ". Atualize a listagem e tente novamente.");
    }
}
