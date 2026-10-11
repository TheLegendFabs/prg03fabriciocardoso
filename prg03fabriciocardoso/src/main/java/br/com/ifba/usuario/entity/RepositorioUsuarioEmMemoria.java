package br.com.ifba.usuario.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositorioUsuarioEmMemoria {

    // Task 01: Repositório usando List com Generics dizendo ao compilador o que existe na coleção[cite: 24]
    private final List<Usuario> usuarios = new ArrayList<>();

    // Task 03: Índice por login usando Map[cite: 25]
    private final Map<String, Usuario> porLogin = new HashMap<>();

    public void cadastrar(Usuario usuario) {
        usuarios.add(usuario);
        porLogin.put(usuario.getLogin(), usuario); // Alimentado a cada cadastro[cite: 25]
    }

    public List<Usuario> listarTodos() {
        return usuarios;
    }

    public Usuario buscarPorLogin(String login) {
        // Task 03: Implementação 1 - Busca percorrendo a List com FOR[cite: 25]
        /*
        for (Usuario u : usuarios) {
            if (u.getLogin().equals(login)) {
                return u;
            }
        }
        return null;
         */

        // Task 03: Implementação 2 - Busca rápida usando o Map[cite: 25]
        return porLogin.get(login);
    }
}
