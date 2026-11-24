package br.com.ifba.usuario.validar;

import br.com.ifba.usuario.entity.Usuario;

public class ValidarCadastroUsuario {
    public void validar(Usuario usuario, String confirmacaoSenha) {
        ValidadorUsuario validador = new ValidadorUsuario();
        if (!validador.camposObrigatoriosPreenchidos(usuario.getNome(), usuario.getCpf(), usuario.getLogin(), usuario.getSenha())) {
            throw new IllegalArgumentException("Preencha nome, CPF, login e senha.");
        }
        if (!validador.senhaConfereComConfirmacao(usuario.getSenha(), confirmacaoSenha)) {
            throw new IllegalArgumentException("As senhas não coincidem.");
        }
        if (!validador.senhaTemTamanhoMinimo(usuario.getSenha())) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 6 caracteres.");
        }
        if (ValidarUsuario.contemPalavraProibida(usuario.getLogin())) {
            throw new IllegalArgumentException("O login contém uma palavra não permitida.");
        }
    }
}
