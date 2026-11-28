package br.com.ifba.usuario.service;

import br.com.ifba.usuario.entity.Usuario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioServiceTest {
    private final FakeDao dao = new FakeDao();
    private final UsuarioService service = new UsuarioService(dao.repository);

    private Usuario usuario() {
        Usuario usuario = new Usuario("Maria", "maria.login", "segura123");
        usuario.setCpf("12345678901");
        return usuario;
    }

    @Test
    void salvaUsuarioValidoSemBanco() {
        Usuario usuario = usuario();
        assertDoesNotThrow(() -> service.save(usuario, "segura123"));
        assertEquals(1, dao.findAll().size());
    }

    @Test
    void recusaConfirmacaoDiferenteSemGravar() {
        assertThrows(IllegalArgumentException.class, () -> service.save(usuario(), "outra123"));
        assertTrue(dao.findAll().isEmpty());
    }

    @Test
    void recusaSenhaFraca() {
        Usuario usuario = usuario();
        usuario.setSenha("123");
        assertThrows(IllegalArgumentException.class, () -> service.save(usuario, "123"));
        assertTrue(dao.findAll().isEmpty());
    }

    @Test
    void recusaLoginProibido() {
        Usuario usuario = usuario();
        usuario.setLogin("admin");
        assertThrows(IllegalArgumentException.class, () -> service.save(usuario, "segura123"));
        assertTrue(dao.findAll().isEmpty());
    }

    @Test
    void recusaCamposVaziosENulos() {
        Usuario usuario = usuario();
        usuario.setCpf("  ");
        assertThrows(IllegalArgumentException.class, () -> service.save(usuario, "segura123"));
        assertThrows(IllegalArgumentException.class, () -> service.save(null, null));
        assertTrue(dao.findAll().isEmpty());
    }

    @Test
    void edicaoSemNovaSenhaPreservaSenhaAnterior() {
        Usuario original = usuario();
        service.save(original, "segura123");
        Usuario alterado = usuario();
        alterado.setId(original.getId());
        alterado.setSenha("");
        alterado.setNome("Maria Silva");
        service.update(alterado, "");
        assertEquals("segura123", service.findById(original.getId()).getSenha());
        assertEquals("Maria Silva", service.findById(original.getId()).getNome());
    }

    @Test
    void listaBuscaEExcluiSemBanco() {
        Usuario usuario = usuario();
        service.save(usuario, "segura123");
        assertEquals(1, service.findByNome("MARIA").size());
        assertEquals(1, service.findAll().size());
        service.delete(usuario.getId());
        assertTrue(service.findAll().isEmpty());
    }

    @Test
    void recusaEdicaoSemIdentificacao() {
        assertThrows(IllegalArgumentException.class, () -> service.update(usuario(), "segura123"));
    }
}
