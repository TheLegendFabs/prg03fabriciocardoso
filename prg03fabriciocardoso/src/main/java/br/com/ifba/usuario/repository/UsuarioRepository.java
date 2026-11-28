package br.com.ifba.usuario.repository;

import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.entity.Status;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByLogin(String login);
    boolean existsByLoginAndIdNot(String login, Long id);

    @Override
    @EntityGraph(attributePaths = "perfis")
    Optional<Usuario> findById(Long id);

    List<Usuario> findByNomeContainingIgnoreCaseOrderByNomeAscIdAsc(String nome);

    @Query("select u from Usuario u where u.status = :status and lower(u.nome) like lower(concat('%', :nome, '%')) order by u.nome, u.id")
    List<Usuario> buscarPorStatusENome(@Param("status") Status status, @Param("nome") String nome);
}
