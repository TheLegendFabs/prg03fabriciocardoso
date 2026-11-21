package br.com.ifba.usuario.service;

import br.com.ifba.usuario.entity.Perfil;
import java.util.List;

public interface PerfilIService {
    void save(Perfil objeto);
    void update(Perfil objeto);
    void delete(Long id);
    Perfil findById(Long id);
    List<Perfil> findAll();
    List<Perfil> findByNome(String nome);
}
