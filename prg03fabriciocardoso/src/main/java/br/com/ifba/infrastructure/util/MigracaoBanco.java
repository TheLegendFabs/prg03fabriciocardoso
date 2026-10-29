package br.com.ifba.infrastructure.util;

import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import org.flywaydb.core.Flyway;
import org.w3c.dom.Element;

public final class MigracaoBanco {
    private MigracaoBanco() {
    }

    public static Map<String, Object> configuracao() {
        try (var arquivo = MigracaoBanco.class.getResourceAsStream("/META-INF/persistence.xml")) {
            if (arquivo == null) {
                throw new IllegalStateException("Configure o arquivo META-INF/persistence.xml local.");
            }
            var leitor = DocumentBuilderFactory.newInstance();
            leitor.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            var documento = leitor.newDocumentBuilder().parse(arquivo);
            var propriedades = documento.getElementsByTagName("property");
            Map<String, Object> configuracao = new HashMap<>();
            for (int i = 0; i < propriedades.getLength(); i++) {
                Element propriedade = (Element) propriedades.item(i);
                configuracao.put(propriedade.getAttribute("name"), propriedade.getAttribute("value"));
            }
            String urlTeste = System.getProperty("poo.jdbc.url");
            if (urlTeste != null) {
                configuracao.put("jakarta.persistence.jdbc.url", urlTeste);
            }
            return configuracao;
        } catch (Exception erro) {
            throw new IllegalStateException("Não foi possível carregar a configuração local do banco.", erro);
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
