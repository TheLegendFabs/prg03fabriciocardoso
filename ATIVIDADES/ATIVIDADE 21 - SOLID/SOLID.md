# Atividade 21 - SOLID

## Exemplos no projeto

- **SRP:** (`prg03fabriciocardoso/src/main/java/br/com/ifba/usuario/controller/UsuarioController.java:7`) UsuarioController cuida das chamadas da tela. As regras de cadastro ficam no UsuarioService.
- **OCP:** (`prg03fabriciocardoso/src/main/java/br/com/ifba/usuario/dao/UsuarioDao.java:13`) UsuarioDao herda GenericDao e pode completar o método preparar, sem mudar o DAO genérico.
- **LSP:** (`prg03fabriciocardoso/src/main/java/br/com/ifba/usuario/entity/Solicitante.java:8`) Solicitante pode ser usado como Usuario. Ele mantém os métodos herdados e informa seu próprio papel.
- **ISP:** (`prg03fabriciocardoso/src/main/java/br/com/ifba/usuario/inteface/Autenticavel.java:4`) Autenticavel tem apenas o método autenticar. Quem usa essa interface não precisa conhecer os métodos de cadastro.
- **DIP:** (`prg03fabriciocardoso/src/main/java/br/com/ifba/usuario/service/UsuarioService.java:7`) UsuarioService usa a interface UsuarioIDao para acessar os dados.

## Três problemas encontrados

1. **SRP:** ValidadorUsuario também tinha um método para autenticar. A classe misturava validação de campos com autenticação.
2. **OCP:** ValidarUsuario só aceitava a lista de palavras escrita dentro do método. Para usar outra lista, era necessário alterar o método.
3. **SRP:** o botão de confirmar da tela antiga TelaCadastroUsuario faz validações, monta o usuário e abre outra tela. Ele tem muitas tarefas juntas. Esse problema ficou para uma próxima melhoria.

## Duas correções

1. Retirei a autenticação de ValidadorUsuario e coloquei em AutenticacaoService. Assim, ValidadorUsuario fica com a validação dos campos.
2. Adicionei em ValidarUsuario um método que recebe a lista de palavras por parâmetro. O método antigo continua usando a lista padrão. Agora é possível verificar outra lista sem alterar a classe.

## Nomes alterados pelo Refactor do NetBeans

| Antes | Depois |
| --- | --- |
| senhaForte | senhaTemTamanhoMinimo |
| camposPreenchidos | camposObrigatoriosPreenchidos |
| senhasCoincidem | senhaConfereComConfirmacao |
| senha1 | senhaInformada |
| senha2 | confirmacaoSenha |

Os nomes ficam em ValidadorUsuario. Eles mostram melhor o que os métodos e parâmetros fazem. Por exemplo, o método senhaForte só verifica se a senha tem pelo menos seis caracteres.

## Comentário removido

Removi o comentário “Método para testar se há campos vazios”, pois o nome do método já explica isso.

## Testes

Foram executados 35 testes, todos aprovados, sem falhas ou erros. O resultado está em resultado_testes.txt e o print está em testes.png, na pasta da atividade.
