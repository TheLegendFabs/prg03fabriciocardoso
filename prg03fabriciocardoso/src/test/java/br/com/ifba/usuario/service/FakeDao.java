package br.com.ifba.usuario.service;

import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

class FakeDao {
    final UsuarioRepository repository = mock(UsuarioRepository.class);
    private final List<Usuario> usuarios = new ArrayList<>();
    private long sequencia;

    FakeDao() {
        when(repository.findAll()).thenAnswer(invocacao -> findAll());
        when(repository.findById(anyLong())).thenAnswer(invocacao -> usuarios.stream()
                .filter(u -> u.getId().equals(invocacao.getArgument(0))).findFirst());
        when(repository.findByNomeContainingIgnoreCaseOrderByNomeAscIdAsc(anyString()))
                .thenAnswer(invocacao -> findByNome(invocacao.getArgument(0)));
        when(repository.existsByLogin(anyString())).thenAnswer(invocacao -> usuarios.stream()
                .anyMatch(u -> u.getLogin().equals(invocacao.getArgument(0))));
        when(repository.existsByLoginAndIdNot(anyString(), anyLong())).thenAnswer(invocacao -> usuarios.stream()
                .anyMatch(u -> u.getLogin().equals(invocacao.getArgument(0)) && !u.getId().equals(invocacao.getArgument(1))));
        when(repository.saveAndFlush(any(Usuario.class))).thenAnswer(invocacao -> {
            Usuario usuario = invocacao.getArgument(0);
            if (usuario.getId() == null) { usuario.setId(++sequencia); }
            usuarios.removeIf(u -> u.getId().equals(usuario.getId()));
            usuarios.add(usuario);
            return usuario;
        });
        doAnswer(invocacao -> { usuarios.remove(invocacao.getArgument(0)); return null; })
                .when(repository).delete(any(Usuario.class));
    }

    List<Usuario> findAll() { return new ArrayList<>(usuarios); }
    List<Usuario> findByNome(String nome) {
        String busca = nome.toLowerCase(Locale.ROOT);
        return usuarios.stream().filter(u -> u.getNome().toLowerCase(Locale.ROOT).contains(busca)).toList();
    }
}
