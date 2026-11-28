package br.com.ifba.usuario.service;

import br.com.ifba.usuario.entity.Perfil;
import br.com.ifba.usuario.repository.PerfilRepository;
import java.util.List;

@org.springframework.stereotype.Service
public class PerfilService implements PerfilIService {
    private final PerfilRepository dao;
    public PerfilService(PerfilRepository dao) { this.dao = java.util.Objects.requireNonNull(dao); }
    public void delete(Long id) { dao.deleteById(findById(id).getId()); }
    public Perfil findById(Long id) { return dao.findById(id).orElseThrow(() -> new IllegalArgumentException("Registro não encontrado: " + id)); }
    public List<Perfil> findAll() { return dao.findAll(); }
    public List<Perfil> findByNome(String nome) { return dao.findByNomeContainingIgnoreCaseOrderByNomeAscIdAsc(nome == null ? "" : nome.trim()); }
    private void validar(Perfil objeto) {
        if (objeto == null || objeto.getNome() == null || objeto.getNome().isBlank()) {
            throw new IllegalArgumentException("Informe o nome do perfil.");
        }
    }
    public void save(Perfil objeto) { validar(objeto); dao.saveAndFlush(objeto); }
    public void update(Perfil objeto) { validar(objeto); findById(objeto.getId()); dao.saveAndFlush(objeto); }
}
