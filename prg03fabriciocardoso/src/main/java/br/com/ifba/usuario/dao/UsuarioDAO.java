package br.com.ifba.usuario.dao;

import br.com.ifba.infrastructure.util.JPAUtil;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.exception.PersistenciaUsuarioException;
import br.com.ifba.usuario.exception.UsuarioNaoEncontradoException;
import jakarta.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

public class UsuarioDAO {
    public void cadastrar(Usuario usuario) {
        executar(em -> { em.persist(usuario); return null; }, true);
    }

    public void atualizar(Usuario usuario) {
        executar(em -> {
            exigirUsuario(em, usuario.getId());
            em.merge(usuario);
            return null;
        }, true);
    }

    public void excluir(Long id) {
        executar(em -> { em.remove(exigirUsuario(em, id)); return null; }, true);
    }

    public Usuario buscarPorId(Long id) {
        return executar(em -> {
            Usuario usuario = exigirUsuario(em, id);
            usuario.getQuantidadePerfis();
            return usuario;
        }, false);
    }

    public List<Usuario> buscarPorNome(String nome) {
        String termo = nome == null ? "" : nome.trim().toLowerCase(Locale.ROOT);
        String filtro = termo.replace("!", "!!").replace("%", "!%").replace("_", "!_");
        return executar(em -> em.createQuery(
                "select u from Usuario u where lower(u.nome) like :nome escape '!' order by u.nome, u.id", Usuario.class)
                .setParameter("nome", "%" + filtro + "%").getResultList(), false);
    }

    private Usuario exigirUsuario(EntityManager em, Long id) {
        Usuario usuario = id == null ? null : em.find(Usuario.class, id);
        if (usuario == null) {
            throw new UsuarioNaoEncontradoException(String.valueOf(id));
        }
        return usuario;
    }

    private <T> T executar(Function<EntityManager, T> operacao, boolean escrita) {
        EntityManager em = null;
        try {
            em = JPAUtil.criarEntityManager();
            if (escrita) {
                em.getTransaction().begin();
            }
            T resultado = operacao.apply(em);
            if (escrita) {
                em.getTransaction().commit();
            }
            return resultado;
        } catch (RuntimeException erro) {
            if (em != null && em.getTransaction().isActive()) {
                try {
                    em.getTransaction().rollback();
                } catch (RuntimeException falhaRollback) {
                    erro.addSuppressed(falhaRollback);
                }
            }
            if (erro instanceof UsuarioNaoEncontradoException) {
                throw erro;
            }
            for (Throwable causa = erro; causa != null; causa = causa.getCause()) {
                if (causa instanceof SQLException sql && "23505".equals(sql.getSQLState())) {
                    throw new PersistenciaUsuarioException("Já existe um usuário com esse login.", erro);
                }
            }
            throw new PersistenciaUsuarioException("Não foi possível concluir a operação no banco de dados. Verifique a conexão e tente novamente.", erro);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
