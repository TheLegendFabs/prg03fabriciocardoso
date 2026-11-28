package br.com.ifba.usuario.service;

import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.repository.UsuarioRepository;
import br.com.ifba.usuario.exception.PersistenciaUsuarioException;
import br.com.ifba.usuario.exception.UsuarioNaoEncontradoException;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UsuarioIService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = java.util.Objects.requireNonNull(repository);
    }

    public void delete(Long id) {
        Usuario usuario = findById(id);
        try {
            repository.delete(usuario);
        } catch (DataAccessException erro) {
            throw new PersistenciaUsuarioException("Não foi possível excluir o usuário.", erro);
        }
    }

    public Usuario findById(Long id) {
        if (id == null) { throw new UsuarioNaoEncontradoException("null"); }
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new UsuarioNaoEncontradoException(String.valueOf(id)));
        } catch (DataAccessException erro) {
            throw new PersistenciaUsuarioException("Não foi possível consultar o usuário.", erro);
        }
    }

    public List<Usuario> findAll() {
        try {
            return repository.findAll();
        } catch (DataAccessException erro) {
            throw new PersistenciaUsuarioException("Não foi possível listar os usuários.", erro);
        }
    }

    public List<Usuario> findByNome(String nome) {
        try {
            return repository.findByNomeContainingIgnoreCaseOrderByNomeAscIdAsc(nome == null ? "" : nome.trim());
        } catch (DataAccessException erro) {
            throw new PersistenciaUsuarioException("Não foi possível buscar os usuários.", erro);
        }
    }

    public void save(Usuario usuario) { save(usuario, usuario == null ? null : usuario.getSenha()); }
    public void update(Usuario usuario) { update(usuario, usuario == null ? null : usuario.getSenha()); }

    public void save(Usuario usuario, String confirmacao) {
        RegrasUsuario.validar(usuario, confirmacao);
        try {
            if (repository.existsByLogin(usuario.getLogin())) {
                throw new IllegalArgumentException("Já existe um usuário com esse login.");
            }
            repository.saveAndFlush(usuario);
        } catch (DataAccessException erro) {
            throw new PersistenciaUsuarioException("Não foi possível salvar o usuário.", erro);
        }
    }

    public void update(Usuario usuario, String confirmacao) {
        if (usuario == null || usuario.getId() == null) {
            throw new IllegalArgumentException("Selecione um usuário para editar.");
        }
        Usuario anterior = findById(usuario.getId());
        if ((usuario.getSenha() == null || usuario.getSenha().isEmpty()) && (confirmacao == null || confirmacao.isEmpty())) {
            usuario.setSenha(anterior.getSenha());
            confirmacao = usuario.getSenha();
        }
        RegrasUsuario.validar(usuario, confirmacao);
        try {
            if (repository.existsByLoginAndIdNot(usuario.getLogin(), usuario.getId())) {
                throw new IllegalArgumentException("Já existe um usuário com esse login.");
            }
            repository.saveAndFlush(usuario);
        } catch (DataAccessException erro) {
            throw new PersistenciaUsuarioException("Não foi possível alterar o usuário.", erro);
        }
    }
}
