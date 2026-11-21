package br.com.ifba.usuario.service;

import br.com.ifba.usuario.entity.Solicitante;
import br.com.ifba.usuario.dao.SolicitanteIDao;
import java.util.List;

public class SolicitanteService implements SolicitanteIService {
    private final SolicitanteIDao dao;
    public SolicitanteService(SolicitanteIDao dao) { this.dao = java.util.Objects.requireNonNull(dao); }
    public void delete(Long id) { dao.delete(id); }
    public Solicitante findById(Long id) { return dao.findById(id); }
    public List<Solicitante> findAll() { return dao.findAll(); }
    public List<Solicitante> findByNome(String nome) { return dao.findByNome(nome); }
    public void save(Solicitante objeto) { save(objeto, objeto == null ? null : objeto.getSenha()); }
    public void update(Solicitante objeto) { update(objeto, objeto == null ? null : objeto.getSenha()); }
    public void save(Solicitante objeto, String confirmacao) {
        RegrasUsuario.validar(objeto, confirmacao);
        dao.save(objeto);
    }
    public void update(Solicitante objeto, String confirmacao) {
        if (objeto == null || objeto.getId() == null) { throw new IllegalArgumentException("Selecione um usuário para editar."); }
        if ((objeto.getSenha() == null || objeto.getSenha().isEmpty()) && (confirmacao == null || confirmacao.isEmpty())) {
            objeto.setSenha(dao.findById(objeto.getId()).getSenha());
            confirmacao = objeto.getSenha();
        }
        RegrasUsuario.validar(objeto, confirmacao);
        dao.update(objeto);
    }
}
