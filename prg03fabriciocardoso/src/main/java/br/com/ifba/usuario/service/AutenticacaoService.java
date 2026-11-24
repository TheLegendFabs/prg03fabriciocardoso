package br.com.ifba.usuario.service;

import br.com.ifba.usuario.inteface.Autenticavel;

public class AutenticacaoService {
    public boolean autenticar(Autenticavel pessoa, String login, String senha) {
        return pessoa.autenticar(login, senha);
    }
}
