package br.com.ifba.usuario.dao;

import br.com.ifba.infrastructure.dao.GenericDao;
import br.com.ifba.usuario.entity.Perfil;
import java.util.List;

public class PerfilDao extends GenericDao<Perfil> implements PerfilIDao {
    public PerfilDao() { super(Perfil.class); }

    public List<Perfil> findByNome(String nome) { return buscarPorNome(nome); }
}
