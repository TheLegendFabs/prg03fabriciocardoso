# Atividade 23 - Spring Data

UsuarioRepository substitui os DAOs. O Service usa existsByLogin para recusar login repetido. A consulta buscarPorStatusENome usa JPQL para filtrar status e nome, ordenando por nome e ID.

O Spring configura o banco e executa as migrations antes de validar as tabelas. A senha continua em application-local.properties, fora do Git.

Os testes que dependiam diretamente dos DAOs e JPAUtil foram adaptados para repository e contexto Spring. Os cenários anteriores foram mantidos; os demais testes de domínio e validação continuam iguais.

As dependências Spring foram alinhadas na versão 3.5.8 para manter compatibilidade entre Spring Data, Hibernate e Flyway.

Resultado: 38 testes aprovados, sem falhas, erros ou testes ignorados. Os prints testes.png e tela.png mostram o painel real do NetBeans e a aplicação.

Para rodar os testes de banco, use POO_TEST_POSTGRES=true, POO_TEST_MIGRATIONS=true e -Dpoo.jdbc.url=jdbc:postgresql://localhost:5432/chamados_poo_migrations_teste.

## Resumo das alterações

Total de linhas removidas: **514**.

Saída de `git diff --cached --stat` da revisão preparada para o commit:

```text
 ATIVIDADES/ATIVIDADE 23 - SPRING DATA/README.md    |  61 +++++++++++++
 ATIVIDADES/ATIVIDADE 23 - SPRING DATA/tela.png     | Bin 0 -> 19316 bytes
 ATIVIDADES/ATIVIDADE 23 - SPRING DATA/testes.png   | Bin 0 -> 238940 bytes
 prg03fabriciocardoso/pom.xml                       |  42 +++++----
 .../src/main/java/br/com/ifba/Aplicacao.java       |   8 +-
 .../br/com/ifba/infrastructure/dao/GenericDao.java |  93 -------------------
 .../com/ifba/infrastructure/dao/GenericIDao.java   |  12 ---
 .../br/com/ifba/infrastructure/util/JPAUtil.java   |  31 -------
 .../ifba/infrastructure/util/MigracaoBanco.java    |  47 ----------
 .../java/br/com/ifba/usuario/dao/PerfilDao.java    |  11 ---
 .../java/br/com/ifba/usuario/dao/PerfilIDao.java   |   9 --
 .../br/com/ifba/usuario/dao/SolicitanteDao.java    |  30 -------
 .../br/com/ifba/usuario/dao/SolicitanteIDao.java   |   9 --
 .../java/br/com/ifba/usuario/dao/TecnicoDao.java   |  30 -------
 .../java/br/com/ifba/usuario/dao/TecnicoIDao.java  |   9 --
 .../java/br/com/ifba/usuario/dao/UsuarioDao.java   |  31 -------
 .../java/br/com/ifba/usuario/dao/UsuarioIDao.java  |   9 --
 .../ifba/usuario/repository/PerfilRepository.java  |   9 ++
 .../usuario/repository/SolicitanteRepository.java  |   9 ++
 .../ifba/usuario/repository/TecnicoRepository.java |   9 ++
 .../ifba/usuario/repository/UsuarioRepository.java |  24 +++++
 .../br/com/ifba/usuario/service/PerfilService.java |  17 ++--
 .../ifba/usuario/service/SolicitanteService.java   |  20 +++--
 .../com/ifba/usuario/service/TecnicoService.java   |  20 +++--
 .../com/ifba/usuario/service/UsuarioService.java   | 100 ++++++++++++++++-----
 .../src/main/resources/META-INF/persistence.xml    |  11 ---
 .../resources/META-INF/persistence.xml.exemplo     |  11 ---
 .../src/main/resources/application.properties      |  12 +--
 .../ifba/infrastructure/dao/GenericDaoTest.java    |  32 -------
 .../entity/ConsultaUsuarioIntegracaoTest.java      |  40 +++++++++
 .../usuario/entity/MigrationsIntegracaoTest.java   |   6 +-
 .../usuario/entity/UsuarioDAOIntegracaoTest.java   |  39 --------
 .../entity/UsuarioRepositoryIntegracaoTest.java    |  40 +++++++++
 .../java/br/com/ifba/usuario/service/FakeDao.java  |  45 ++++++----
 .../ifba/usuario/service/LoginDuplicadoTest.java   |  18 ++++
 .../usuario/service/PersistenciaUsuarioTest.java   |  40 +++++++++
 .../ifba/usuario/service/UsuarioServiceTest.java   |   2 +-
 .../src/test/java/br/com/ifba/util/BancoTeste.java |  23 +++++
 38 files changed, 445 insertions(+), 514 deletions(-)
```
