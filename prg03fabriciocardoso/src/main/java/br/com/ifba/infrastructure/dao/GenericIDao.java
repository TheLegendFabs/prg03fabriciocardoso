package br.com.ifba.infrastructure.dao;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import java.util.List;

public interface GenericIDao<Entity extends PersistenceEntity> {
    void save(Entity entidade);
    void update(Entity entidade);
    void delete(Long id);
    Entity findById(Long id);
    List<Entity> findAll();
}
