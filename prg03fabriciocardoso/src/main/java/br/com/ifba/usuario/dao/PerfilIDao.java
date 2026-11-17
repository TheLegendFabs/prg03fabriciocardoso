package br.com.ifba.usuario.dao;

import br.com.ifba.infrastructure.dao.GenericIDao;
import br.com.ifba.usuario.entity.Perfil;
import java.util.List;

public interface PerfilIDao extends GenericIDao<Perfil> {
    List<Perfil> findByNome(String nome);
}
