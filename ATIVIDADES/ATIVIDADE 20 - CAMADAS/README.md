# Atividade 20 - Arquitetura em camadas

Usuario, Perfil, Tecnico e Solicitante possuem Service, IService, Controller e IController em br.com.ifba.usuario.service e br.com.ifba.usuario.controller, seguindo o domínio das entidades existentes.

Service recebe IDao pelo construtor, Controller recebe IService e as telas recebem UsuarioIController. Aplicacao monta a cadeia de dependências e abre a listagem; os formulários existentes continuam em .java e .form do NetBeans.

As regras de campos obrigatórios, senha mínima, confirmação de senha, login reservado e manutenção da senha na edição são executadas no Service. As telas coletam os campos, chamam o Controller e apresentam o resultado com JOptionPane.

A listagem coordena cadastro, consulta, edição e exclusão; os formulários de cadastro e edição salvam pelo mesmo Controller e atualizam a listagem.

FakeDao implementa UsuarioIDao com List em memória; UsuarioServiceTest verifica as regras sem abrir conexão ou utilizar JPA.

A busca por imports de EntityManager e do pacote dao em arquivos de view não encontra resultados; as credenciais continuam no persistence.xml local ignorado pelo Git.

Execute br.com.ifba.Aplicacao no NetBeans para iniciar o CRUD.
