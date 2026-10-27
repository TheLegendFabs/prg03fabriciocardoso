# Atividade 16 - JPA e Hibernate (Parte 1)

A numeracao 16 foi adotada conforme orientacao do aluno; o PDF da aula 17 usa a numeracao 14. O projeto continua no modulo Maven `prg03fabriciocardoso`, como nas atividades anteriores.

## Task 01 - Configuracao

Hibernate ORM e driver PostgreSQL estao no pom.xml. PostgreSQL 18 esta instalado localmente.
Copie `src/main/resources/META-INF/persistence.xml.exemplo` para `persistence.xml` na mesma pasta e preencha URL, usuario e senha localmente. Exemplo de URL: `jdbc:postgresql://localhost:5432/chamados`. Crie o banco antes de conectar.
O arquivo real esta ignorado pelo Git; o exemplo tem os campos de conexao em branco. Nenhuma credencial e publicada.
A configuracao usa `hibernate.hbm2ddl.auto=none` para nao alterar automaticamente o banco existente. Nesta Parte 1, as telas nao abrem conexao nem fazem CRUD no banco.

## Task 02 - Entidades

Usuario tem @Entity, @Table, @Id e @GeneratedValue. O status e armazenado por nome (EnumType.STRING).
Perfil foi mapeado como entidade com construtor vazio; perfilAtivo usa ManyToOne e perfis usa ManyToMany com tabela de associacao. Tecnico e Solicitante usam heranca SINGLE_TABLE, preservando o polimorfismo anterior.
Os construtores e a igualdade por login foram preservados.

## Task 03 - Pacotes e imagens

As entidades ficam em `br.com.ifba.usuario.entity` e as novas telas em `br.com.ifba.usuario.view`.
As imagens ficam em `src/main/resources/br/com/ifba/login/images`, carregadas como recursos do classpath. As telas anteriores de login e cadastro foram preservadas.

## Task 04 - Telas

Execute TelaListagemUsuario.java pelo NetBeans (Run File). Ela abre o cadastro e a edicao, e apresenta confirmacao de exclusao com JOptionPane.
As tres telas possuem arquivos .java e .form para o GUI Builder do NetBeans. Cadastro e edicao reaproveitam o layout, campos e componentes do cadastro anterior. A tabela inicia vazia; busca, salvar e excluir ficam para a Parte 2.

## Task 05 - Testes

A bateria original tem 18 testes. O pom foi ajustado para Java 25, instalado no computador, e AbsoluteLayout passou a usar coordenadas publicas do NetBeans no Maven Central no lugar do repositorio local ausente.
Execute `mvn clean test` dentro do modulo. O teste adicional de metadados valida os mapeamentos Hibernate sem banco nem credenciais.

## Task 06 - DTO

Resposta em duas linhas em RETROSPECTIVA.md, na raiz do repositorio.
