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
    
    @Test
    public void testStatusInicialCorreto() {
        // Objeto recém-criado nasce com o status correto
        Usuario usuario = new Usuario();
        assertEquals(Status.INATIVO, usuario.getStatus());
    }

    @Test
    public void testAdicionarItemFazListaCrescer() {
        // Ao adicionar um item, a lista cresce
        Usuario usuario = new Usuario();
        Perfil perfil = new Perfil("Suporte", "Acesso aos chamados");

        usuario.adicionarPerfil(perfil);
        assertEquals(1, usuario.getQuantidadePerfis());
    }

    @Test
    public void testObjetoRelacionadoDevolvidoPeloGetter() {
        // O objeto relacionado é devolvido pelo getter
        Usuario usuario = new Usuario();
        Perfil perfil = new Perfil("Admin", "Acesso total");

        usuario.setPerfilAtivo(perfil);
        assertEquals(perfil, usuario.getPerfilAtivo());
    }
}
