package br.com.ifba.usuario.service;

import br.com.ifba.usuario.entity.Perfil;
import br.com.ifba.usuario.dao.PerfilIDao;
import java.util.List;

public class PerfilService implements PerfilIService {
    private final PerfilIDao dao;
    public PerfilService(PerfilIDao dao) { this.dao = java.util.Objects.requireNonNull(dao); }
    public void delete(Long id) { dao.delete(id); }
    public Perfil findById(Long id) { return dao.findById(id); }
    public List<Perfil> findAll() { return dao.findAll(); }
    public List<Perfil> findByNome(String nome) { return dao.findByNome(nome); }
    private void validar(Perfil objeto) {
        if (objeto == null || objeto.getNome() == null || objeto.getNome().isBlank()) {
            throw new IllegalArgumentException("Informe o nome do perfil.");
        }
    }
    public void save(Perfil objeto) { validar(objeto); dao.save(objeto); }
    public void update(Perfil objeto) { validar(objeto); dao.update(objeto); }
}
