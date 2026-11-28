package br.com.ifba.usuario.repository;

import br.com.ifba.usuario.entity.Solicitante;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitanteRepository extends JpaRepository<Solicitante, Long> {
    List<Solicitante> findByNomeContainingIgnoreCaseOrderByNomeAscIdAsc(String nome);
}
