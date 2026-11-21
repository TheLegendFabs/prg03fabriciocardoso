package br.com.ifba.usuario.controller;

import br.com.ifba.usuario.entity.Tecnico;
import br.com.ifba.usuario.service.TecnicoIService;
import java.util.List;

public class TecnicoController implements TecnicoIController {
    private final TecnicoIService service;
    public TecnicoController(TecnicoIService service) { this.service = java.util.Objects.requireNonNull(service); }
    public void save(Tecnico objeto) { service.save(objeto); }
    public void update(Tecnico objeto) { service.update(objeto); }
    public void delete(Long id) { service.delete(id); }
    public Tecnico findById(Long id) { return service.findById(id); }
    public List<Tecnico> findAll() { return service.findAll(); }
    public List<Tecnico> findByNome(String nome) { return service.findByNome(nome); }
    public void save(Tecnico objeto, String confirmacao) { service.save(objeto, confirmacao); }
    public void update(Tecnico objeto, String confirmacao) { service.update(objeto, confirmacao); }
}
