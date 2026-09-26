package br.com.ifba.usuario.entity;

public class Solicitante extends Usuario {

    @Override
    public String obterPapel() {
        return "Solicitante de Suporte";
    }
}
