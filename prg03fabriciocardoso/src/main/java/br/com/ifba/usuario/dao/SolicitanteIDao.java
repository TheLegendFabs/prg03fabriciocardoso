package br.com.ifba.usuario.dao;

import br.com.ifba.infrastructure.dao.GenericIDao;
import br.com.ifba.usuario.entity.Solicitante;
import java.util.List;

public interface SolicitanteIDao extends GenericIDao<Solicitante> {
    List<Solicitante> findByNome(String nome);
}
