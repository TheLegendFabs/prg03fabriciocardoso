package br.com.ifba.usuario.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("SOLICITANTE")
public class Solicitante extends Usuario {

    @Override
    public String obterPapel() {
        return "Solicitante de Suporte";
    }
}
