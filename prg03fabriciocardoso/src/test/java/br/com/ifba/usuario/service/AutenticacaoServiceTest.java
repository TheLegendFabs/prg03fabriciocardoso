package br.com.ifba.usuario.service;

import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.entity.Tecnico;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AutenticacaoServiceTest {
    private final AutenticacaoService service = new AutenticacaoService();

    @Test
    void autenticaUsuarioComCredenciaisValidas() {
        Usuario usuario = new Usuario("Maria", "maria", "123456");
        assertTrue(service.autenticar(usuario, "maria", "123456"));
        assertFalse(service.autenticar(usuario, "maria", "incorreta"));
    }

    @Test
    void preservaComportamentoDoTecnico() {
        Tecnico tecnico = new Tecnico();
        tecnico.setLogin("fabricio");
        tecnico.setSenha("123456");
        assertTrue(service.autenticar(tecnico, "fabricio", "senha_mestre_ti"));
        assertFalse(service.autenticar(tecnico, "outro", "senha_mestre_ti"));
    }
}
