package br.com.ifba.usuario.controller;

import br.com.ifba.usuario.entity.Usuario;
import java.util.List;

public interface UsuarioIController {
    void save(Usuario objeto);
    void update(Usuario objeto);
    void delete(Long id);
    Usuario findById(Long id);
    List<Usuario> findAll();
    List<Usuario> findByNome(String nome);
    void save(Usuario objeto, String confirmacao);
    void update(Usuario objeto, String confirmacao);
}
