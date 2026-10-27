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
        Usuario usuario = porLogin.get(login);
        if (usuario == null) {
            throw new br.com.ifba.usuario.exception.UsuarioNaoEncontradoException(login);
        }
        return usuario;
    }
}
