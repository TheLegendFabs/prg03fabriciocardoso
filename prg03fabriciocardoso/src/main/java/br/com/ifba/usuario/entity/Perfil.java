package br.com.ifba.usuario.entity;

import jakarta.persistence.*;
import br.com.ifba.infrastructure.entity.PersistenceEntity;

@Entity
@Table(name = "perfis")
public class Perfil extends PersistenceEntity {


    public Perfil() {
    }


    private String nome;
    private String descricao;

    public Perfil(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
