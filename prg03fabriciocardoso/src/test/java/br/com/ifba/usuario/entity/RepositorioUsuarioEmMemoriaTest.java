package br.com.ifba.usuario.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RepositorioUsuarioEmMemoriaTest {

    @Test
    public void testCadastrarEListarTodos() {
        RepositorioUsuarioEmMemoria repo = new RepositorioUsuarioEmMemoria();
        Usuario u = new Usuario("Maria", "maria.login", "123");

        repo.cadastrar(u);

        assertEquals(1, repo.listarTodos().size());
        assertTrue(repo.listarTodos().contains(u));
    }

    @Test
    public void testCadastrarDoisEBuscarPorLogin() {
        RepositorioUsuarioEmMemoria repo = new RepositorioUsuarioEmMemoria();
        Usuario u1 = new Usuario("Maria", "maria.login", "123");
        Usuario u2 = new Usuario("João", "joao.login", "456");

        repo.cadastrar(u1);
        repo.cadastrar(u2);

        assertEquals(u2, repo.buscarPorLogin("joao.login"));
    }

    @Test
    public void testBuscarPorLoginInexistente() {
        RepositorioUsuarioEmMemoria repo = new RepositorioUsuarioEmMemoria();
        assertNull(repo.buscarPorLogin("nao.existe"));
    }

    @Test
    public void testEqualsComMesmoLogin() {
        // Task 04: Prova que dois objetos diferentes com o mesmo login são iguais para o sistema
        Usuario u1 = new Usuario("Maria", "maria.login", "123");
        Usuario u2 = new Usuario("Maria Clone", "maria.login", "999");

        assertEquals(u1, u2);
    }
}
