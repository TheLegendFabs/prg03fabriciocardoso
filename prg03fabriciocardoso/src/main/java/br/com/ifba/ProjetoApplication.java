package br.com.ifba;

import br.com.ifba.usuario.view.TelaListagemUsuario;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ProjetoApplication {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            ConfigurableApplicationContext context =
                    new SpringApplicationBuilder(ProjetoApplication.class)
                            .headless(false)
                            .run(args);
            TelaListagemUsuario tela = context.getBean(TelaListagemUsuario.class);
            tela.setVisible(true);
        });
    }
}
