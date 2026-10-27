package br.com.ifba.usuario.exception;

public class PersistenciaUsuarioException extends RuntimeException {
    public PersistenciaUsuarioException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
