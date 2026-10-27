package br.com.ifba.usuario.entity;

import br.com.ifba.usuario.dao.UsuarioDAO;
import br.com.ifba.usuario.exception.PersistenciaUsuarioException;
import br.com.ifba.usuario.exception.UsuarioNaoEncontradoException;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import static org.junit.jupiter.api.Assertions.*;

@EnabledIfEnvironmentVariable(named = "POO_TEST_POSTGRES", matches = "true")
class UsuarioDAOIntegracaoTest {
    @Test
    void crudBuscaRollbackERegistroInexistente() {
        UsuarioDAO dao = new UsuarioDAO();
        String chave = "teste_" + UUID.randomUUID();
        Usuario usuario = new Usuario("Teste " + chave, chave, "teste123");
        try {
            dao.cadastrar(usuario);
            assertNotNull(usuario.getId());
            assertTrue(dao.buscarPorNome(chave).stream().anyMatch(u -> u.getId().equals(usuario.getId())));
            Usuario editado = dao.buscarPorId(usuario.getId());
            editado.setNome("Editado % _ " + chave);
            dao.atualizar(editado);
            assertEquals(editado.getNome(), dao.buscarPorId(usuario.getId()).getNome());
            assertTrue(dao.buscarPorNome("% _ " + chave).stream().anyMatch(u -> u.getId().equals(usuario.getId())));
            assertThrows(PersistenciaUsuarioException.class,
                    () -> dao.cadastrar(new Usuario("Duplicado", chave, "teste123")));
            assertEquals(1, dao.buscarPorNome(chave).size());
            dao.excluir(usuario.getId());
            assertThrows(UsuarioNaoEncontradoException.class, () -> dao.buscarPorId(usuario.getId()));
            assertTrue(dao.buscarPorNome(chave).isEmpty());
        } finally {
            for (Usuario restante : dao.buscarPorNome(chave)) {
                dao.excluir(restante.getId());
            }
        }
    }
}
