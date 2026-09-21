package br.com.ifba.usuario.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    public void testAutenticarCredenciaisCorretas() {
        // Prepara o objeto com dados simulados do banco
        Usuario usuario = new Usuario();
        usuario.setLogin("admin");
        usuario.setSenha("123");

        // Caminho feliz: Login e senha batem, tem que liberar o acesso (TRUE)
        assertTrue(usuario.autenticar("admin", "123"));
    }

    @Test
    public void testAutenticarSenhaIncorreta() {
        // Prepara o objeto
        Usuario usuario = new Usuario();
        usuario.setLogin("admin");
        usuario.setSenha("123");

        // Limite/Falha: Senha errada, tem que negar o acesso (FALSE)
        assertFalse(usuario.autenticar("admin", "senhaerrada"));
    }
}
