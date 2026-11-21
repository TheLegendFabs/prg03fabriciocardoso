package br.com.ifba.usuario.dao;

import br.com.ifba.infrastructure.dao.GenericDao;
import br.com.ifba.usuario.entity.Solicitante;
import java.util.List;

public class SolicitanteDao extends GenericDao<Solicitante> implements SolicitanteIDao {
    public SolicitanteDao() { super(Solicitante.class); }

    public List<Solicitante> findByNome(String nome) { return buscarPorNome(nome); }

    @Override
    protected void preparar(Solicitante usuario) { usuario.getQuantidadePerfis(); }

    @Override
    protected RuntimeException naoEncontrado(Long id) {
        return new br.com.ifba.usuario.exception.UsuarioNaoEncontradoException(String.valueOf(id));
    }

    @Override
    protected RuntimeException traduzirFalha(RuntimeException erro) {
        if (erro instanceof br.com.ifba.infrastructure.exception.DaoException) { return erro; }
        for (Throwable causa = erro; causa != null; causa = causa.getCause()) {
            if (causa instanceof java.sql.SQLException sql && "23505".equals(sql.getSQLState())) {
                return new br.com.ifba.usuario.exception.PersistenciaUsuarioException("Já existe um usuário com esse login.", erro);
            }
        }
        return new br.com.ifba.usuario.exception.PersistenciaUsuarioException("Não foi possível concluir a operação no banco de dados.", erro);
    }
}
