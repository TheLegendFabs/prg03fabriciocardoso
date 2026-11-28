package br.com.ifba.usuario.repository;

import br.com.ifba.usuario.entity.Perfil;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    List<Perfil> findByNomeContainingIgnoreCaseOrderByNomeAscIdAsc(String nome);
}
