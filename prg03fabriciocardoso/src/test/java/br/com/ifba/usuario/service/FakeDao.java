package br.com.ifba.usuario.service;

import br.com.ifba.usuario.dao.UsuarioIDao;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.exception.UsuarioNaoEncontradoException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class FakeDao implements UsuarioIDao {
    private final List<Usuario> usuarios = new ArrayList<>();
    private long sequencia;
    public void save(Usuario objeto) { objeto.setId(++sequencia); usuarios.add(objeto); }
    public void update(Usuario objeto) {
        Usuario anterior = findById(objeto.getId());
        usuarios.set(usuarios.indexOf(anterior), objeto);
    }
    public void delete(Long id) { usuarios.remove(findById(id)); }
    public Usuario findById(Long id) {
        return usuarios.stream().filter(u -> u.getId().equals(id)).findFirst()
                .orElseThrow(() -> new UsuarioNaoEncontradoException(String.valueOf(id)));
    }
    public List<Usuario> findAll() { return new ArrayList<>(usuarios); }
    public List<Usuario> findByNome(String nome) {
        String busca = nome == null ? "" : nome.toLowerCase(Locale.ROOT);
        return usuarios.stream().filter(u -> u.getNome().toLowerCase(Locale.ROOT).contains(busca)).toList();
    }
}
