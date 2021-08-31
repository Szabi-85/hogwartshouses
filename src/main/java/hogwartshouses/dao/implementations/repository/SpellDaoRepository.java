package hogwartshouses.dao.implementations.repository;

import hogwartshouses.model.entity.SpellEntity;
import org.springframework.data.repository.CrudRepository;

public interface SpellDaoRepository extends CrudRepository<SpellEntity, Long> {
}