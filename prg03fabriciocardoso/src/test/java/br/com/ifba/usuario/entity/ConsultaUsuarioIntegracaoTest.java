package br.com.ifba.usuario.entity;

import br.com.ifba.usuario.repository.UsuarioRepository;
import br.com.ifba.util.BancoTeste;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import static org.junit.jupiter.api.Assertions.*;

@EnabledIfEnvironmentVariable(named = "POO_TEST_POSTGRES", matches = "true")
class ConsultaUsuarioIntegracaoTest {
    @Test
    void consultaDerivadaVerificaLoginExistente() {
        UsuarioRepository repository = BancoTeste.contexto().getBean(UsuarioRepository.class);
        String login = "consulta_" + UUID.randomUUID();
        assertFalse(repository.existsByLogin(login));
        Usuario usuario = repository.saveAndFlush(new Usuario("Maria", login, "segura123"));
        try { assertTrue(repository.existsByLogin(login)); }
        finally { repository.deleteById(usuario.getId()); }
        assertFalse(repository.existsByLogin(login));
    }

    @Test
    void consultaJpqlFiltraStatusENomeEOrdena() {
        UsuarioRepository repository = BancoTeste.contexto().getBean(UsuarioRepository.class);
        String chave = UUID.randomUUID().toString();
        List<Usuario> salvos = new ArrayList<>();
        try {
            for (String nome : List.of("Zelia "+chave, "Ana "+chave, "Inativa "+chave, "Fora do filtro")) {
                Usuario usuario = new Usuario(nome, UUID.randomUUID().toString(), "segura123");
                usuario.setStatus(nome.startsWith("Inativa") ? Status.INATIVO : Status.ATIVO);
                salvos.add(repository.saveAndFlush(usuario));
            }
            List<Usuario> encontrados = repository.buscarPorStatusENome(Status.ATIVO, chave);
            assertEquals(List.of("Ana "+chave, "Zelia "+chave), encontrados.stream().map(Usuario::getNome).toList());
        } finally { salvos.forEach(usuario -> repository.deleteById(usuario.getId())); }
    }
}
