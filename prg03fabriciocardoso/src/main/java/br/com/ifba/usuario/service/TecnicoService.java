package br.com.ifba.usuario.service;

import br.com.ifba.usuario.entity.Tecnico;
import br.com.ifba.usuario.repository.TecnicoRepository;
import java.util.List;

@org.springframework.stereotype.Service
public class TecnicoService implements TecnicoIService {
    private final TecnicoRepository dao;
    public TecnicoService(TecnicoRepository dao) { this.dao = java.util.Objects.requireNonNull(dao); }
    public void delete(Long id) { dao.deleteById(findById(id).getId()); }
    public Tecnico findById(Long id) { return dao.findById(id).orElseThrow(() -> new IllegalArgumentException("Registro não encontrado: " + id)); }
    public List<Tecnico> findAll() { return dao.findAll(); }
    public List<Tecnico> findByNome(String nome) { return dao.findByNomeContainingIgnoreCaseOrderByNomeAscIdAsc(nome == null ? "" : nome.trim()); }
    public void save(Tecnico objeto) { save(objeto, objeto == null ? null : objeto.getSenha()); }
    public void update(Tecnico objeto) { update(objeto, objeto == null ? null : objeto.getSenha()); }
    public void save(Tecnico objeto, String confirmacao) {
        RegrasUsuario.validar(objeto, confirmacao);
        dao.saveAndFlush(objeto);
    }
    public void update(Tecnico objeto, String confirmacao) {
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
