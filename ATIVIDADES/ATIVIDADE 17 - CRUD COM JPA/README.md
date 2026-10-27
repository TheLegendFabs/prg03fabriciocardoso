# Atividade 17 - CRUD com JPA

## Fábrica de conexões

`br.com.ifba.infrastructure.util.JPAUtil` centraliza uma única EntityManagerFactory e fornece EntityManager por operação; a fábrica é encerrada ao terminar a aplicação.

## CRUD e busca

As telas de `br.com.ifba.usuario.view` mantêm os formulários .form do NetBeans e utilizam UsuarioDAO: cadastrar usa persist, editar usa merge e excluir usa remove, sempre com begin/commit.
A listagem busca por nome com JPQL, where e parâmetro; busca vazia lista todos, sem diferenciar maiúsculas e minúsculas. Os caracteres % e _ são tratados como texto.
Cadastro e edição atualizam a listagem ao salvar. É necessário selecionar uma linha para editar ou excluir; a exclusão pede confirmação. Na edição, deixar os dois campos de senha vazios mantém a senha atual.

## Exceções e recursos

`UsuarioNaoEncontradoException` substitui o retorno null de `RepositorioUsuarioEmMemoria.buscarPorLogin`; sem verificar esse retorno, o código poderia acessar um usuário inexistente e lançar NullPointerException.
O DAO também lança essa exceção quando um ID não existe; as telas capturam a exceção e mostram a mensagem com JOptionPane.
Falhas de persistência recebem mensagem em português; login duplicado é identificado pela restrição do banco. O DAO faz rollback em caso de transação aberta e fecha o EntityManager no finally.

## Banco local

O banco exclusivo é `chamados_poo`; as credenciais ficam somente no persistence.xml local ignorado pelo Git. Para outro ambiente, copie persistence.xml.exemplo para persistence.xml e preencha a conexão.
`hibernate.hbm2ddl.auto=update` prepara as tabelas no banco selecionado; utilize um banco exclusivo do projeto. O arquivo de exemplo não contém credenciais.
Execute `TelaListagemUsuario.java` no NetBeans para iniciar o CRUD.

## Verificação

`mvn clean test` executa os testes de domínio, incluindo assertThrows para login inexistente e assertDoesNotThrow para usuário existente.
O teste UsuarioDAOIntegracaoTest só executa com a variável POO_TEST_POSTGRES=true e a conexão local configurada; valida persist, merge, remove, busca, login duplicado e recuperação após rollback, removendo os registros temporários ao terminar.
