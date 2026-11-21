package br.com.ifba.usuario.service;

import br.com.ifba.usuario.entity.Tecnico;
import br.com.ifba.usuario.dao.TecnicoIDao;
import java.util.List;

public class TecnicoService implements TecnicoIService {
    private final TecnicoIDao dao;
    public TecnicoService(TecnicoIDao dao) { this.dao = java.util.Objects.requireNonNull(dao); }
    public void delete(Long id) { dao.delete(id); }
    public Tecnico findById(Long id) { return dao.findById(id); }
    public List<Tecnico> findAll() { return dao.findAll(); }
    public List<Tecnico> findByNome(String nome) { return dao.findByNome(nome); }
    public void save(Tecnico objeto) { save(objeto, objeto == null ? null : objeto.getSenha()); }
    public void update(Tecnico objeto) { update(objeto, objeto == null ? null : objeto.getSenha()); }
    public void save(Tecnico objeto, String confirmacao) {
        RegrasUsuario.validar(objeto, confirmacao);
        dao.save(objeto);
    }
    public void update(Tecnico objeto, String confirmacao) {
        if (objeto == null || objeto.getId() == null) { throw new IllegalArgumentException("Selecione um usuário para editar."); }
        if ((objeto.getSenha() == null || objeto.getSenha().isEmpty()) && (confirmacao == null || confirmacao.isEmpty())) {
            objeto.setSenha(dao.findById(objeto.getId()).getSenha());
            confirmacao = objeto.getSenha();
        }
        RegrasUsuario.validar(objeto, confirmacao);
        dao.update(objeto);
    }
}
