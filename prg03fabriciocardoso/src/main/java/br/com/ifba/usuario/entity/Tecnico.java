package br.com.ifba.usuario.entity;

public class Tecnico extends Usuario {

    @Override
    public String obterPapel() {
        return "Técnico de TI";
    }
}
