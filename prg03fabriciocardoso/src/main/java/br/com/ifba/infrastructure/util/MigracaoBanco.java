package br.com.ifba.infrastructure.util;

import java.util.HashMap;
import java.util.Map;
import org.flywaydb.core.Flyway;

public final class MigracaoBanco {
    private MigracaoBanco() {
    }

    public static Map<String, Object> configuracao() {
        java.util.Properties propriedades = new java.util.Properties();
        try {
            for (String nome : new String[] {"/application.properties", "/application-local.properties"}) {
                try (var arquivo = MigracaoBanco.class.getResourceAsStream(nome)) {
                    if (arquivo != null) {
                        propriedades.load(new java.io.InputStreamReader(arquivo, java.nio.charset.StandardCharsets.UTF_8));
                    }
                }
            }
            Map<String, Object> configuracao = new HashMap<>();
            for (String nome : propriedades.stringPropertyNames()) {
                if (nome.startsWith("jakarta.persistence.") || nome.startsWith("hibernate.")) {
                    configuracao.put(nome, propriedades.getProperty(nome));
                }
            }
            String urlTeste = System.getProperty("poo.jdbc.url");
            if (urlTeste != null) {
                configuracao.put("jakarta.persistence.jdbc.url", urlTeste);
            }
            return configuracao;
        } catch (Exception erro) {
            throw new IllegalStateException("Não foi possível carregar application.properties.", erro);
        }
    }

    public static void migrar(Map<String, Object> configuracao) {
        Flyway.configure()
                .dataSource((String) configuracao.get("jakarta.persistence.jdbc.url"),
                        (String) configuracao.get("jakarta.persistence.jdbc.user"),
                        (String) configuracao.get("jakarta.persistence.jdbc.password"))
                .locations("classpath:db/migration")
                .cleanDisabled(true)
                .load()
                .migrate();
    }
}
