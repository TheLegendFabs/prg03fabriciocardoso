package br.com.ifba.usuario.entity;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MapeamentoJpaTest {
    @Test
    void validaEntidadesRelacionamentosEHerancaSemConectarAoBanco() {
        var registro = new StandardServiceRegistryBuilder()
                .applySetting("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
                .applySetting("hibernate.boot.allow_jdbc_metadata_access", "false")
                .build();
        try {
            var metadata = new MetadataSources(registro)
                    .addAnnotatedClass(Usuario.class)
                    .addAnnotatedClass(Perfil.class)
                    .addAnnotatedClass(Tecnico.class)
                    .addAnnotatedClass(Solicitante.class)
                    .buildMetadata();
            try (var fabrica = metadata.buildSessionFactory()) {
                assertNotNull(fabrica.getMetamodel().entity(Usuario.class).getId(Long.class));
                assertEquals(4, fabrica.getMetamodel().getEntities().size());
            }
        } finally {
            StandardServiceRegistryBuilder.destroy(registro);
        }
    }
}
