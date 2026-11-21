package br.com.ifba.usuario.controller;

import br.com.ifba.usuario.entity.Solicitante;
import br.com.ifba.usuario.service.SolicitanteIService;
import java.util.List;

public class SolicitanteController implements SolicitanteIController {
    private final SolicitanteIService service;
    public SolicitanteController(SolicitanteIService service) { this.service = java.util.Objects.requireNonNull(service); }
    public void save(Solicitante objeto) { service.save(objeto); }
    public void update(Solicitante objeto) { service.update(objeto); }
    public void delete(Long id) { service.delete(id); }
    public Solicitante findById(Long id) { return service.findById(id); }
    public List<Solicitante> findAll() { return service.findAll(); }
    public List<Solicitante> findByNome(String nome) { return service.findByNome(nome); }
    public void save(Solicitante objeto, String confirmacao) { service.save(objeto, confirmacao); }
    public void update(Solicitante objeto, String confirmacao) { service.update(objeto, confirmacao); }
}
