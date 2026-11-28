package br.com.ifba.usuario.service;

import br.com.ifba.usuario.entity.Solicitante;
import br.com.ifba.usuario.repository.SolicitanteRepository;
import java.util.List;

@org.springframework.stereotype.Service
public class SolicitanteService implements SolicitanteIService {
    private final SolicitanteRepository dao;
    public SolicitanteService(SolicitanteRepository dao) { this.dao = java.util.Objects.requireNonNull(dao); }
    public void delete(Long id) { dao.deleteById(findById(id).getId()); }
    public Solicitante findById(Long id) { return dao.findById(id).orElseThrow(() -> new IllegalArgumentException("Registro não encontrado: " + id)); }
    public List<Solicitante> findAll() { return dao.findAll(); }
    public List<Solicitante> findByNome(String nome) { return dao.findByNomeContainingIgnoreCaseOrderByNomeAscIdAsc(nome == null ? "" : nome.trim()); }
    public void save(Solicitante objeto) { save(objeto, objeto == null ? null : objeto.getSenha()); }
    public void update(Solicitante objeto) { update(objeto, objeto == null ? null : objeto.getSenha()); }
    public void save(Solicitante objeto, String confirmacao) {
        RegrasUsuario.validar(objeto, confirmacao);
        dao.saveAndFlush(objeto);
    }
    public void update(Solicitante objeto, String confirmacao) {
        if (objeto == null || objeto.getId() == null) { throw new IllegalArgumentException("Selecione um usuário para editar."); }
        if ((objeto.getSenha() == null || objeto.getSenha().isEmpty()) && (confirmacao == null || confirmacao.isEmpty())) {
            objeto.setSenha(findById(objeto.getId()).getSenha());
            confirmacao = objeto.getSenha();
        }
        findById(objeto.getId());
        RegrasUsuario.validar(objeto, confirmacao);
        dao.saveAndFlush(objeto);
    }
}
