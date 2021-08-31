package hogwartshouses.dao.implementations.repository;

import hogwartshouses.model.entity.RoomEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoomDaoRepository extends CrudRepository<RoomEntity, Long> {
}