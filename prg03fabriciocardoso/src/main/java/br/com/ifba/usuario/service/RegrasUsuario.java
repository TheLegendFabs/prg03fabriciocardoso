package br.com.ifba.usuario.service;

import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.validar.ValidarUsuario;

final class RegrasUsuario {
    private RegrasUsuario() { }
    static void validar(Usuario usuario, String confirmacao) {
        if (usuario == null || vazio(usuario.getNome()) || vazio(usuario.getCpf()) || vazio(usuario.getLogin()) || vazio(usuario.getSenha())) {
            throw new IllegalArgumentException("Preencha nome, CPF, login e senha.");
        }
        if (!usuario.getSenha().equals(confirmacao)) {
            throw new IllegalArgumentException("As senhas não coincidem.");
        }
        if (usuario.getSenha().length() < 6) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 6 caracteres.");
        }
        if (ValidarUsuario.contemPalavraProibida(usuario.getLogin())) {
            throw new IllegalArgumentException("O login contém uma palavra não permitida.");
        }
    }
    private static boolean vazio(String valor) { return valor == null || valor.isBlank(); }
}
