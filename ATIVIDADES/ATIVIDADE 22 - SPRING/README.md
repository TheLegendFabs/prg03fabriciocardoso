# Atividade 22 - Spring

O projeto inicia por ProjetoApplication. O Spring monta TelaListagemUsuario, UsuarioController, UsuarioService e UsuarioDao pelos construtores. A interface continua sendo a mesma feita no NetBeans, sem servidor web.

As telas usam @Component, o controller usa @Controller, o service usa @Service e o DAO usa @Repository. Cadastro e edição possuem mais de um construtor, por isso @Autowired indica apenas o construtor usado pelo Spring, sem injeção em atributos.

A configuração do banco fica em application.properties. A senha fica em application-local.properties, ignorado pelo Git. O persistence.xml fica apenas com a unidade de persistência e as entidades.

Foi usado Spring Boot 3.2.0, como no slide. A compilação foi ajustada para Java 21, compatível com essa versão; o JDK 25 instalado continua executando o projeto.

Os testes existentes não foram alterados. Os prints mostram os testes antes, depois e a aplicação iniciada pelo Spring.

Resultado: 35 testes aprovados antes e 35 depois, sem falhas, erros ou testes ignorados.

Prints: testes_antes.png, testes_depois.png e tela_spring.png. A abertura da tela foi conferida pelo Executar do NetBeans, configurado em nbactions.xml para ProjetoApplication.

Para configurar em outra máquina, crie src/main/resources/application-local.properties com a propriedade jakarta.persistence.jdbc.password e a senha local do PostgreSQL.
