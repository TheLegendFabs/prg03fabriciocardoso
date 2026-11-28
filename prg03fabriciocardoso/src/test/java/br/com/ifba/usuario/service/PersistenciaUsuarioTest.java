package br.com.ifba.usuario.service;

import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.repository.UsuarioRepository;
import br.com.ifba.usuario.exception.PersistenciaUsuarioException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessResourceFailureException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

class PersistenciaUsuarioTest {
    private final UsuarioRepository repository = mock(UsuarioRepository.class);
    private final UsuarioService service = new UsuarioService(repository);
    private Usuario usuario() {
        Usuario usuario = new Usuario("Maria", "maria", "segura123");
        usuario.setCpf("12345678901");
        return usuario;
    }
    @Test
    void saveTraduzFalhaDeConexao() {
        when(repository.saveAndFlush(any())).thenThrow(new DataAccessResourceFailureException("Conexão indisponível"));
        assertThrows(PersistenciaUsuarioException.class, () -> service.save(usuario()));
    }
    @Test
    void updateTraduzFalhaDeConexao() {
        Usuario usuario = usuario(); usuario.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(usuario));
        when(repository.saveAndFlush(any())).thenThrow(new DataAccessResourceFailureException("Conexão indisponível"));
        assertThrows(PersistenciaUsuarioException.class, () -> service.update(usuario));
    }
    @Test
    void deleteTraduzFalhaDeConexao() {
        Usuario usuario = usuario(); usuario.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(usuario));
        doThrow(new DataAccessResourceFailureException("Conexão indisponível")).when(repository).delete(usuario);
        assertThrows(PersistenciaUsuarioException.class, () -> service.delete(1L));
    }
}
