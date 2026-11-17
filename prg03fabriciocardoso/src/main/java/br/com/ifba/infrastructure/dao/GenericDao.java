package br.com.ifba.infrastructure.dao;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import br.com.ifba.infrastructure.exception.DaoException;
import br.com.ifba.infrastructure.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

public class GenericDao<Entity extends PersistenceEntity> implements GenericIDao<Entity> {
    private final Class<Entity> tipo;

    public GenericDao(Class<Entity> tipo) { this.tipo = tipo; }

    public void save(Entity entidade) {
        executar(em -> { em.persist(entidade); return null; }, true);
    }

    public void update(Entity entidade) {
        executar(em -> { exigir(em, entidade.getId()); em.merge(entidade); return null; }, true);
    }

    public void delete(Long id) {
        executar(em -> { em.remove(exigir(em, id)); return null; }, true);
    }

    public Entity findById(Long id) {
        return executar(em -> { Entity entidade = exigir(em, id); preparar(entidade); return entidade; }, false);
    }

    public List<Entity> findAll() {
        return executar(em -> em.createQuery("select e from " + tipo.getSimpleName() + " e order by e.id", tipo).getResultList(), false);
    }

    protected List<Entity> buscarPorNome(String nome) {
        String filtro = (nome == null ? "" : nome.trim()).toLowerCase(Locale.ROOT)
                .replace("!", "!!").replace("%", "!%").replace("_", "!_");
        return executar(em -> em.createQuery("select e from " + tipo.getSimpleName()
                + " e where lower(e.nome) like :nome escape '!' order by e.nome, e.id", tipo)
                .setParameter("nome", "%" + filtro + "%").getResultList(), false);
    }

    protected void preparar(Entity entidade) { }

    protected EntityManager criarEntityManager() { return JPAUtil.criarEntityManager(); }

    protected RuntimeException naoEncontrado(Long id) {
        return new DaoException(tipo.getSimpleName() + " não encontrado: " + id, null);
    }

    protected RuntimeException traduzirFalha(RuntimeException erro) {
        if (erro instanceof DaoException) { return erro; }
        return new DaoException("Não foi possível concluir a operação no banco de dados.", erro);
    }

    private Entity exigir(EntityManager em, Long id) {
        Entity entidade = id == null ? null : em.find(tipo, id);
        if (entidade == null) { throw naoEncontrado(id); }
        return entidade;
    }

    protected <T> T executar(Function<EntityManager, T> operacao, boolean escrita) {
        EntityManager em = null;
        try {
            em = criarEntityManager();
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
            if (erro instanceof DaoException) {
                throw erro;
            }
            throw traduzirFalha(erro);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
