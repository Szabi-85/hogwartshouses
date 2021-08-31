package hogwartshouses.dao.implementations.repository;

import hogwartshouses.model.entity.BuildingEntity;
import org.springframework.data.repository.CrudRepository;

public interface BuildingDaoRepository extends CrudRepository<BuildingEntity, Long> {
}