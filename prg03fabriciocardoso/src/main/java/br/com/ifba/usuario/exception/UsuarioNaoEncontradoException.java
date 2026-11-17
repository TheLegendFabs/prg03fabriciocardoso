package br.com.ifba.usuario.exception;

public class UsuarioNaoEncontradoException extends br.com.ifba.infrastructure.exception.DaoException {
    public UsuarioNaoEncontradoException(String identificacao) {
        super("Usuário não encontrado: " + identificacao + ". Atualize a listagem e tente novamente.", null);
    }
}
