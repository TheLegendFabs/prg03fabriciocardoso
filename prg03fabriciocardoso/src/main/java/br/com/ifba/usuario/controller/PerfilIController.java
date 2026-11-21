package br.com.ifba.usuario.controller;

import br.com.ifba.usuario.entity.Perfil;
import java.util.List;

public interface PerfilIController {
    void save(Perfil objeto);
    void update(Perfil objeto);
    void delete(Long id);
    Perfil findById(Long id);
    List<Perfil> findAll();
    List<Perfil> findByNome(String nome);
}
