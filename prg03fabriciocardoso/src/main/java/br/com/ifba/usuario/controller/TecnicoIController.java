package br.com.ifba.usuario.controller;

import br.com.ifba.usuario.entity.Tecnico;
import java.util.List;

public interface TecnicoIController {
    void save(Tecnico objeto);
    void update(Tecnico objeto);
    void delete(Long id);
    Tecnico findById(Long id);
    List<Tecnico> findAll();
    List<Tecnico> findByNome(String nome);
    void save(Tecnico objeto, String confirmacao);
    void update(Tecnico objeto, String confirmacao);
}
