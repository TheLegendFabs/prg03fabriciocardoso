package br.com.ifba.usuario.service;

import br.com.ifba.usuario.entity.Usuario;
import java.util.List;

public interface UsuarioIService {
    void save(Usuario objeto);
    void update(Usuario objeto);
    void delete(Long id);
    Usuario findById(Long id);
    List<Usuario> findAll();
    List<Usuario> findByNome(String nome);
    void save(Usuario objeto, String confirmacao);
    void update(Usuario objeto, String confirmacao);
}
