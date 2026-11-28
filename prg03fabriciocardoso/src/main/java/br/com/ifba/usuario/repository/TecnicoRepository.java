package br.com.ifba.usuario.repository;

import br.com.ifba.usuario.entity.Tecnico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {
    List<Tecnico> findByNomeContainingIgnoreCaseOrderByNomeAscIdAsc(String nome);
}
