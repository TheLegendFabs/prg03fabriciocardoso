package br.com.ifba.usuario.controller;

import br.com.ifba.usuario.entity.Perfil;
import br.com.ifba.usuario.service.PerfilIService;
import java.util.List;

public class PerfilController implements PerfilIController {
    private final PerfilIService service;
    public PerfilController(PerfilIService service) { this.service = java.util.Objects.requireNonNull(service); }
    public void save(Perfil objeto) { service.save(objeto); }
    public void update(Perfil objeto) { service.update(objeto); }
    public void delete(Long id) { service.delete(id); }
    public Perfil findById(Long id) { return service.findById(id); }
    public List<Perfil> findAll() { return service.findAll(); }
    public List<Perfil> findByNome(String nome) { return service.findByNome(nome); }
}
