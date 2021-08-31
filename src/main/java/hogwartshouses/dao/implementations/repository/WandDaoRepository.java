package hogwartshouses.dao.implementations.repository;

import hogwartshouses.model.entity.WandEntity;
import org.springframework.data.repository.CrudRepository;

public interface WandDaoRepository extends CrudRepository<WandEntity, Long> {
}