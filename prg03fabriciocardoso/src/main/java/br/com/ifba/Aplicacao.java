package br.com.ifba;

import br.com.ifba.usuario.dao.*;
import br.com.ifba.usuario.service.*;
import br.com.ifba.usuario.controller.*;
import br.com.ifba.usuario.view.TelaListagemUsuario;

public final class Aplicacao {
    private Aplicacao() { }
    public static void main(String[] args) {
        var controller = new UsuarioController(new UsuarioService(new UsuarioDao()));
        java.awt.EventQueue.invokeLater(() -> new TelaListagemUsuario(controller).setVisible(true));
    }
}
