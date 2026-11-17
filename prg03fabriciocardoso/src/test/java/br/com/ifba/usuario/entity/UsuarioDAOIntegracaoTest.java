package br.com.ifba.usuario.entity;

import br.com.ifba.usuario.dao.UsuarioDao;
import br.com.ifba.usuario.exception.PersistenciaUsuarioException;
import br.com.ifba.usuario.exception.UsuarioNaoEncontradoException;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import static org.junit.jupiter.api.Assertions.*;

@EnabledIfEnvironmentVariable(named = "POO_TEST_POSTGRES", matches = "true")
class UsuarioDaoIntegracaoTest {
    @Test
    void crudBuscaRollbackERegistroInexistente() {
        UsuarioDao dao = new UsuarioDao();
        String chave = "teste_" + UUID.randomUUID();
        Usuario usuario = new Usuario("Teste " + chave, chave, "teste123");
        try {
            dao.save(usuario);
            assertNotNull(usuario.getId());
            assertTrue(dao.findByNome(chave).stream().anyMatch(u -> u.getId().equals(usuario.getId())));
            Usuario editado = dao.findById(usuario.getId());
            editado.setNome("Editado % _ " + chave);
            dao.update(editado);
            assertEquals(editado.getNome(), dao.findById(usuario.getId()).getNome());
            assertTrue(dao.findByNome("% _ " + chave).stream().anyMatch(u -> u.getId().equals(usuario.getId())));
            assertThrows(PersistenciaUsuarioException.class,
                    () -> dao.save(new Usuario("Duplicado", chave, "teste123")));
            assertEquals(1, dao.findByNome(chave).size());
            dao.delete(usuario.getId());
            assertThrows(UsuarioNaoEncontradoException.class, () -> dao.findById(usuario.getId()));
            assertTrue(dao.findByNome(chave).isEmpty());
        } finally {
            for (Usuario restante : dao.findByNome(chave)) {
                dao.delete(restante.getId());
            }
        }
    }
}
