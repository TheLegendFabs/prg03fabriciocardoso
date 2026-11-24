package br.com.ifba.usuario.service;

import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.dao.UsuarioIDao;
import java.util.List;

@org.springframework.stereotype.Service
public class UsuarioService implements UsuarioIService {
    private final UsuarioIDao dao;
    public UsuarioService(UsuarioIDao dao) { this.dao = java.util.Objects.requireNonNull(dao); }
    public void delete(Long id) { dao.delete(id); }
    public Usuario findById(Long id) { return dao.findById(id); }
    public List<Usuario> findAll() { return dao.findAll(); }
    public List<Usuario> findByNome(String nome) { return dao.findByNome(nome); }
    public void save(Usuario objeto) { save(objeto, objeto == null ? null : objeto.getSenha()); }
    public void update(Usuario objeto) { update(objeto, objeto == null ? null : objeto.getSenha()); }
    public void save(Usuario objeto, String confirmacao) {
        RegrasUsuario.validar(objeto, confirmacao);
        dao.save(objeto);
    }
    public void update(Usuario objeto, String confirmacao) {
        if (objeto == null || objeto.getId() == null) { throw new IllegalArgumentException("Selecione um usuário para editar."); }
        if ((objeto.getSenha() == null || objeto.getSenha().isEmpty()) && (confirmacao == null || confirmacao.isEmpty())) {
            objeto.setSenha(dao.findById(objeto.getId()).getSenha());
            confirmacao = objeto.getSenha();
        }
        RegrasUsuario.validar(objeto, confirmacao);
        dao.update(objeto);
    }
}
