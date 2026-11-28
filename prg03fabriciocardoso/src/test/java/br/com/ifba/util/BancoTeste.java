package br.com.ifba.util;

import br.com.ifba.ProjetoApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public final class BancoTeste {
    private static ConfigurableApplicationContext contexto;
    private BancoTeste() { }
    public static synchronized ConfigurableApplicationContext contexto() {
        if (contexto == null) {
            String url = System.getProperty("poo.jdbc.url", "");
            if (!url.endsWith("/chamados_poo_migrations_teste")) {
                throw new IllegalStateException("Use apenas o banco de testes.");
            }
            contexto = new SpringApplicationBuilder(ProjetoApplication.class).headless(true).run();
        }
        return contexto;
    }
    public static synchronized void fechar() {
        if (contexto != null) { contexto.close(); contexto = null; }
    }
}
