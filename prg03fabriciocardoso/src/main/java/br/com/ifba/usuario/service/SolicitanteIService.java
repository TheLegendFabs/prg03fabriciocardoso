package br.com.ifba.usuario.service;

import br.com.ifba.usuario.entity.Solicitante;
import java.util.List;

public interface SolicitanteIService {
    void save(Solicitante objeto);
    void update(Solicitante objeto);
    void delete(Long id);
    Solicitante findById(Long id);
    List<Solicitante> findAll();
    List<Solicitante> findByNome(String nome);
    void save(Solicitante objeto, String confirmacao);
    void update(Solicitante objeto, String confirmacao);
}
