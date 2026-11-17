package br.com.ifba.infrastructure.exception;

public class DaoException extends RuntimeException {
    public DaoException(String mensagem, Throwable causa) { super(mensagem, causa); }
}
