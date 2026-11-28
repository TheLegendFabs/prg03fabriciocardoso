package br.com.ifba.usuario.entity;

import br.com.ifba.usuario.repository.UsuarioRepository;
import br.com.ifba.util.BancoTeste;
import org.springframework.dao.DataIntegrityViolationException;
import br.com.ifba.usuario.exception.UsuarioNaoEncontradoException;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import static org.junit.jupiter.api.Assertions.*;

@EnabledIfEnvironmentVariable(named = "POO_TEST_POSTGRES", matches = "true")
class UsuarioRepositoryIntegracaoTest {
    @Test
    void crudBuscaRollbackERegistroInexistente() {
        UsuarioRepository dao = BancoTeste.contexto().getBean(UsuarioRepository.class);
        String chave = "teste_" + UUID.randomUUID();
        Usuario usuario = new Usuario("Teste " + chave, chave, "teste123");
        try {
            dao.saveAndFlush(usuario);
            assertNotNull(usuario.getId());
            assertTrue(dao.findByNomeContainingIgnoreCaseOrderByNomeAscIdAsc(chave).stream().anyMatch(u -> u.getId().equals(usuario.getId())));
            Usuario editado = dao.findById(usuario.getId()).orElseThrow(() -> new UsuarioNaoEncontradoException("ausente"));
            editado.setNome("Editado % _ " + chave);
            dao.saveAndFlush(editado);
            assertEquals(editado.getNome(), dao.findById(usuario.getId()).orElseThrow(() -> new UsuarioNaoEncontradoException("ausente")).getNome());
            assertTrue(dao.findByNomeContainingIgnoreCaseOrderByNomeAscIdAsc("% _ " + chave).stream().anyMatch(u -> u.getId().equals(usuario.getId())));
            assertThrows(DataIntegrityViolationException.class,
                    () -> dao.saveAndFlush(new Usuario("Duplicado", chave, "teste123")));
            assertEquals(1, dao.findByNomeContainingIgnoreCaseOrderByNomeAscIdAsc(chave).size());
            dao.deleteById(usuario.getId());
            assertThrows(UsuarioNaoEncontradoException.class, () -> dao.findById(usuario.getId()).orElseThrow(() -> new UsuarioNaoEncontradoException("ausente")));
            assertTrue(dao.findByNomeContainingIgnoreCaseOrderByNomeAscIdAsc(chave).isEmpty());
        } finally {
            for (Usuario restante : dao.findByNomeContainingIgnoreCaseOrderByNomeAscIdAsc(chave)) {
                dao.deleteById(restante.getId());
            }
        }
    }
}
