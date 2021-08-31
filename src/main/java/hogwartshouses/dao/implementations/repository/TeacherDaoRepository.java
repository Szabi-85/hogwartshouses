package hogwartshouses.dao.implementations.repository;

import hogwartshouses.model.entity.TeacherEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TeacherDaoRepository extends CrudRepository<TeacherEntity, Long> {
    @Query(value = "SELECT * FROM teacher INNER JOIN wand ON teacher.wand_id = wand.id WHERE wand.wood_type = :woodType",
            nativeQuery = true)
    List<TeacherEntity> listTeacherByWandWoodType(@Param("woodType") String woodType);
}