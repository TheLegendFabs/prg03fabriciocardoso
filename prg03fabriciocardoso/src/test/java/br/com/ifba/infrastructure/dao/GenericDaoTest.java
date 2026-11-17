package br.com.ifba.infrastructure.dao;

import br.com.ifba.infrastructure.exception.DaoException;
import br.com.ifba.usuario.entity.Usuario;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GenericDaoTest {
    private final GenericDao<Usuario> dao = new GenericDao<>(Usuario.class) {
        @Override
        protected EntityManager criarEntityManager() {
            throw new IllegalStateException("Conexão indisponível no teste");
        }
    };

    @Test
    void saveTraduzFalhaDeConexao() {
        var erro = assertThrows(DaoException.class, () -> dao.save(new Usuario()));
        assertInstanceOf(IllegalStateException.class, erro.getCause());
    }

    @Test
    void updateTraduzFalhaDeConexao() {
        assertThrows(DaoException.class, () -> dao.update(new Usuario()));
    }

    @Test
    void deleteTraduzFalhaDeConexao() {
        assertThrows(DaoException.class, () -> dao.delete(1L));
    }
}
