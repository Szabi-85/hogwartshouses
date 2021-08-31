package hogwartshouses.dao.implementations.repository;

import hogwartshouses.model.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface StudentDaoRepository extends CrudRepository<StudentEntity, Long> {
}