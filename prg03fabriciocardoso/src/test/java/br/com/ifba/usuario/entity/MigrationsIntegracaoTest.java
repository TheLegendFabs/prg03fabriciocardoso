package br.com.ifba.usuario.entity;

import br.com.ifba.util.BancoTeste;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import static org.junit.jupiter.api.Assertions.*;

@EnabledIfEnvironmentVariable(named = "POO_TEST_MIGRATIONS", matches = "true")
class MigrationsIntegracaoTest {
    @Test
    void aplicaTresMigrationsEValidaNovamenteSemReaplicar() {
        conferirHistorico();
        BancoTeste.fechar();
        conferirHistorico();
    }

    private void conferirHistorico() {
        var em = BancoTeste.contexto().getBean(jakarta.persistence.EntityManagerFactory.class).createEntityManager();
        try {
            var historico = em.createNativeQuery("select version from flyway_schema_history where type = 'SQL' and success order by installed_rank", String.class).getResultList();
            assertEquals(java.util.List.of("1", "2", "3"), historico);
            assertEquals("NO", em.createNativeQuery("select is_nullable from information_schema.columns where table_schema = 'public' and table_name = 'usuarios' and column_name = 'login'").getSingleResult());
            assertEquals("NO", em.createNativeQuery("select is_nullable from information_schema.columns where table_schema = 'public' and table_name = 'usuarios' and column_name = 'data_cadastro'").getSingleResult());
        } finally {
            em.close();
        }
    }
}
