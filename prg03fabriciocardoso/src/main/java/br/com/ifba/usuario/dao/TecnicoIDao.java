package br.com.ifba.usuario.dao;

import br.com.ifba.infrastructure.dao.GenericIDao;
import br.com.ifba.usuario.entity.Tecnico;
import java.util.List;

public interface TecnicoIDao extends GenericIDao<Tecnico> {
    List<Tecnico> findByNome(String nome);
}
