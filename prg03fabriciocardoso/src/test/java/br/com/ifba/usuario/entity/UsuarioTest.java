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
    
    @Test
    public void testComportamentoHerdadoDaMae() {
        // Tecnico é a filha, mas consegue usar os métodos da mãe (Usuario)
        Tecnico tecnico = new Tecnico();
        tecnico.setLogin("fabricio.tec");
        tecnico.setSenha("senha123");

        assertTrue(tecnico.autenticar("fabricio.tec", "senha123"));
    }

    @Test
    public void testComportamentoSobrescritoDasFilhas() {
        // Cada classe deve devolver o seu próprio resultado
        Usuario usuarioComum = new Usuario();
        Tecnico tecnico = new Tecnico();
        Solicitante solicitante = new Solicitante();

        assertEquals("Usuário Padrão", usuarioComum.obterPapel());
        assertEquals("Técnico de TI", tecnico.obterPapel());
        assertEquals("Solicitante de Suporte", solicitante.obterPapel());
    }
    
    @Test
    public void testAutenticarFormaPadrao() {
        // Task 05: Tipo geral à esquerda, chamando a forma padrão da classe mãe
        br.com.ifba.usuario.inteface.Autenticavel pessoaComum = new Usuario("Maria", "maria.comum", "123");

        // Autentica normalmente com a senha 123
        assertTrue(pessoaComum.autenticar("maria.comum", "123"));
    }

    @Test
    public void testAutenticarFormaTecnicoComSenhaMestre() {
        // Task 05: Tipo geral à esquerda, mas instanciando a classe filha
        Usuario objTecnico = new Tecnico();
        objTecnico.setLogin("fabricio.admin");
        objTecnico.setSenha("senha_normal_dele");

        br.com.ifba.usuario.inteface.Autenticavel pessoaTecnica = objTecnico;

        // Usa a segunda forma do método (Polimorfismo): Loga usando a senha mestre, ignorando a senha normal
        assertTrue(pessoaTecnica.autenticar("fabricio.admin", "senha_mestre_ti"));
    }
}
