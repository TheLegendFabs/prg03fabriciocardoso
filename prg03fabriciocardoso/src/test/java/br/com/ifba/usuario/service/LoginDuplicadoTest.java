package br.com.ifba.usuario.service;

import br.com.ifba.usuario.entity.Usuario;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginDuplicadoTest {
    @Test
    void recusaLoginJaCadastradoSemGravarOutroUsuario() {
        FakeDao dados = new FakeDao();
        UsuarioService service = new UsuarioService(dados.repository);
        Usuario primeiro = new Usuario("Maria", "maria", "segura123"); primeiro.setCpf("12345678901");
        service.save(primeiro);
        Usuario segundo = new Usuario("Outra Maria", "maria", "segura123"); segundo.setCpf("22222222222");
        assertThrows(IllegalArgumentException.class, () -> service.save(segundo));
        assertEquals(1, dados.findAll().size());
    }
}
