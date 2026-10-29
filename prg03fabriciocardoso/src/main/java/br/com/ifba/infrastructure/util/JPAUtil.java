package br.com.ifba.infrastructure.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class JPAUtil {
    private static EntityManagerFactory fabrica;

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(JPAUtil::fechar));
    }

    private JPAUtil() {
    }

    public static synchronized EntityManager criarEntityManager() {
        if (fabrica == null || !fabrica.isOpen()) {
            var configuracao = MigracaoBanco.configuracao();
            MigracaoBanco.migrar(configuracao);
            fabrica = Persistence.createEntityManagerFactory("chamadosPU", configuracao);
        }
        return fabrica.createEntityManager();
    }

    public static synchronized void fechar() {
        if (fabrica != null && fabrica.isOpen()) {
            fabrica.close();
        }
    }
}
