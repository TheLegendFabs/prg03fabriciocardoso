package br.com.ifba.usuario.controller;

import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.service.UsuarioIService;
import java.util.List;

@org.springframework.stereotype.Controller
public class UsuarioController implements UsuarioIController {
    private final UsuarioIService service;
    public UsuarioController(UsuarioIService service) { this.service = java.util.Objects.requireNonNull(service); }
    public void save(Usuario objeto) { service.save(objeto); }
    public void update(Usuario objeto) { service.update(objeto); }
    public void delete(Long id) { service.delete(id); }
    public Usuario findById(Long id) { return service.findById(id); }
    public List<Usuario> findAll() { return service.findAll(); }
    public List<Usuario> findByNome(String nome) { return service.findByNome(nome); }
    public void save(Usuario objeto, String confirmacao) { service.save(objeto, confirmacao); }
    public void update(Usuario objeto, String confirmacao) { service.update(objeto, confirmacao); }
}
