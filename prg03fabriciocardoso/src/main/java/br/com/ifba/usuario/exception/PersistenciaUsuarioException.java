package br.com.ifba.usuario.exception;

public class PersistenciaUsuarioException extends br.com.ifba.infrastructure.exception.DaoException {
    public PersistenciaUsuarioException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
