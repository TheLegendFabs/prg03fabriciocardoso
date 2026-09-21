package br.com.ifba.usuario.validar;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidadorUsuarioTest {

    // Instancia o validador para usarmos nos testes
    ValidadorUsuario validador = new ValidadorUsuario();

    // --- TESTES DA SENHA FORTE ---
    @Test
    public void testSenhaForteCorreta() {
        // Caminho feliz: Senha com 6 ou mais caracteres tem que retornar TRUE
        assertTrue(validador.senhaForte("123456"));
    }

    @Test
    public void testSenhaForteIncorreta() {
        // Limite: Senha menor que 6 caracteres tem que retornar FALSE
        assertFalse(validador.senhaForte("12345"));
    }

    @Test
    public void testSenhaForteNula() {
        // Limite: Senha nula tem que retornar FALSE para evitar erro no sistema (NullPointerException)
        assertFalse(validador.senhaForte(null));
    }

    // --- TESTES DE CAMPOS PREENCHIDOS ---
    @Test
    public void testCamposPreenchidosCorretamente() {
        // Caminho feliz: Todos os campos preenchidos tem que retornar TRUE
        assertTrue(validador.camposPreenchidos("Fabricio", "123456789", "admin", "123456"));
    }

    @Test
    public void testCamposPreenchidosComCampoVazio() {
        // Limite: Pelo menos um campo vazio tem que retornar FALSE
        assertFalse(validador.camposPreenchidos("Fabricio", "", "admin", "123456"));
    }
}
